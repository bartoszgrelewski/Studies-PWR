# Bartosz Grelewski 261725

using SparseArrays
using LinearAlgebra


# Method that reads matrix data from given file.

# Parameters:
# filepath - is a relative path to file and type is String

# Return:
# (A, n, l) where:
# A - matrix; 
# n - size of matrix A; 
# l - size of submatrices
function load_matrix(filepath::String)
    local data = C_NULL

    # it's method from library which handles files reading
    open(filepath, "r") do file
        sizes = split(readline(file), " ")
        n = parse(Int64, sizes[1])
        l = parse(Int64, sizes[2])

        # Matrix A is sparse array type.
        A = spzeros(Float64, n, n)

        # whole line is being read and then split into 3 parts
        for line in eachline(file)
            data = split(line, " ")
            i = parse(Int64, data[1])
            j = parse(Int64, data[2])
            value = parse(Float64, data[3])

            A[i, j] = value
        end

        data = (A, n, l)
    end

    return data
end


# Method that reads vector of right sides from given file.

# Parameters:
# filepath - is path to file with data; is String

# Return:
# (b, n) where:
# b - vector of right sides; 
# n size of vector b
function load_vector(filepath::String)

    # CNULL is a null pointer in C language and is used to initialize variables 
    # which will be used later on
    local data = C_NULL
    local b = C_NULL
    local n = C_NULL

    open(filepath, "r") do file
        n = parse(Int64, readline(file))

        # Vector b
        b = Vector{Float64}(undef,n)
        i = 1

        for line in eachline(file)
            b[i] = parse(Float64, line)
            i += 1
        end    
    end

    data = (b, n)
    return data
end



# writes results to file in given path 

# Parameters:
# filpath - path to file where results should be saved; is String
# x - vector with arguments; is Vector{Float64}
# n - length of given vector x; is Int64
# is_b_gen - determinates type of b vector: if true b was generated, false - b was read from file

# Return: nothing
function write_results(filepath::String, x::Vector{Float64}, n::Int64, is_b_gen::Bool)
    local err::Float64

    open(filepath, "w") do file
        if is_b_gen 
            x_initial = ones(n)

            # calculates error of solution
            err = norm(x_initial - x) / norm(x)
            println(file, err)
        end

        for i = 1:n
            println(file, x[i])
        end
    end
end


# Method that generates vector b from matrix A.

# Parameters:
# A - matrix; is Sparse Matrix; is SparseMatrixCSC{Float64, Int64}
# n - size of matrix A; is Int64
# l - size of submatrices of A; is Int64

# Returns:
# b - generated vector b; is Vector{Float64}
function create_right_side_vector(A::SparseMatrixCSC{Float64, Int64}, n::Int64, l::Int64)
    b = Vector{Float64}(undef,n)

    # Iteration through rows of matrix A 
    for i = 1:n
        b[i] = Float64(0.0)

        # Iteration through columns of matrix A
        for j = max(1, Int64(l * floor((i - 1) / l)) - 1):min(n, Int64(l + l * floor((i - 1) / l)))
            b[i] += A[i, j]
        end 
            
        if i + l <= n # if i + l is in range of matrix A
            # adds element to b[i] if it's in range of matrix A 
            b[i] += A[i, i + l]
        end
    end

    # returns generated vector b
    return b
end


# Method that solves set of linear equations using gaussian elimination method.

# Parameters:
# A - matrix; is Sparse Matrix; is SparseMatrixCSC{Float64, Int64}
# b - vector with values; is Vector{Float64}
# n - size of matrix A; is Int64
# l - size of submatrices of A; is Int64

# Return:
# (A, b)
# A - given matrix after elimination, is SparseMatrixCSC{Float64, Int64};
# b - given vector of right sides after elimination, is Vector{Float64}
# series which is used to optimalize gauss elimination method
# 4,3,2,4, 4,3,2,4 -> k+l
function gauss(A::SparseMatrixCSC{Float64, Int64}, b::Vector{Float64}, n::Int64, l::Int64)
    # Iteration through columns
    for k = 1:n - 1

        # Iteration through elements to delete
        for i = (k+1):min(k + l, n)

            # Multiplier = element to delete / current element on diagonal
            multiplier = A[i, k] / A[k, k]
            A[i, k] = Float64(0.0)
            
            # Iteration through columns
            for j = k + 1:min(k + l, n)
                # Elimination of elements in matrix A and vector b
                A[i, j] -= multiplier * A[k, j]
            end
                # Iteration through rows of vector b
                b[i] -= multiplier * b[k]
            end
        end

    # Returns matrix A and vector b after elimination
    return (A, b)
end


# Method that solves set of linear equations using gaussian elimination method.

# Parameters:
# A - matrix; is Sparse Matrix; is SparseMatrixCSC{Float64, Int64}
# b - vector with values; is Vector{Float64}
# n - size of matrix A; is Int64
# l - size of submatrices of A; is Int64

# Return:
# x - vector with solutions; if Vector{Float64}
function solve_gauss(A::SparseMatrixCSC{Float64, Int64}, b::Vector{Float64}, n::Int64, l::Int64)
    res = gauss(A, b, n, l)
    
    _A = res[1]
    _b = res[2]

    # Vector with solutions of equations set
    x = Vector{Float64}(undef, n)

    # Iteration through rows of matrix A
    for i = n:-1:1
        sum_from_row = Float64(0.0)

        # Iteration through columns of matrix A
        for j = i + 1:min(n, i + l)
            # Calculates sum of elements in row
            sum_from_row += _A[i, j] * x[j]
        end
        # Calculates solution of equation set using calculated sum of 
        # elements in row and diagonal element of matrix A 
        x[i] = (_b[i] - sum_from_row) / _A[i, i]
    end
    
    # Returns vector with solutions of equations set 
    return x
end

# Makes gaussian elimiantion with choose of main element on given matrix. 
# If is_lu is set function makes LU decompsition using gaussian elimiantion with choose of main element.

# Parameters:
# A - matrix; is Sparse Matrix; is SparseMatrixCSC{Float64, Int64}
# b - vector with values; is Vector{Float64}
# n - size of matrix A; is Int64
# l - size of submatrices of A; is Int64

# Return:
# (A, perm, b) where 
# A - given matrix after elimination, is SparseMatrixCSC{Float64, Int64};
# perm - permutation vector, is Vector{Float64};
# b - given vector of right sides after elimination, is Vector{Float64}
function gauss_with_choose(A::SparseMatrixCSC{Float64, Int64}, b::Vector{Float64}, n::Int64, l::Int64)

    # Permutation vector
    perm = Vector{Int64}(undef, n)

    # Put not permutated sequence into perm
    for i = 1:n
        perm[i] = i
    end

    # Iteration trough columns
    for k = 1:n - 1

        # Row with maximal element
        max_row = k

        # Maximal element in column
        max_element = abs(A[k, k])

        # Iteration trough elements to eliminate
        for i = k:min(k + l, n)
            # If element is bigger than max_element then it becomes max_element and max_row is set to i
            if abs(A[perm[i], k]) > max_element
                max_element = abs(A[perm[i], k])
                max_row = i
            end
        end
        
        # Swap rows in matrix A and vector b
        perm[k], perm[max_row] = perm[max_row], perm[k]

        # Iteration trough elements to eliminate
        for i = k + 1:min(k + l, n)
            # Multiplier = element to delete / current element on diagonal 
            multiplier = A[perm[i], k] / A[perm[k], k]

            # Set element to delete to 0 
            A[perm[i], k] = Float64(0.0)
            
            # Iteration trough columns
            for j = k + 1:min(k + 2 * l, n)
                # Elimination of elements in matrix A and vector b
                A[perm[i], j] -= multiplier * A[perm[k], j]
            end
            # Elimination of elements in vector b
            b[perm[i]] -= multiplier * b[perm[k]]
        
        end 
    end

    # Returns matrix A, permutation vector and vector b after elimination
    return (A, perm, b)
end



# Method that solves set of linear equations using gaussian elimination method with choose of main element.

# Parameters:
# A - matrix; is Sparse Matrix; is SparseMatrixCSC{Float64, Int64}
# b - vector with values; is Vector{Float64}
# n - size of matrix A; is Int64
# l - size of submatrices of A; is Int64

# Return:
# x - vector with solutions; if Vector{Float64}
function solve_gauss_with_choose(A::SparseMatrixCSC{Float64, Int64}, b::Vector{Float64}, n::Int64, l::Int64)
    res = gauss_with_choose(A, b, n, l)

    _A = res[1]
    _perm = res[2]
    _b = res[3]

    #Vector with solutions of equations set
    x = Vector{Float64}(undef, n)

    # Iteration through rows
    for i = n:-1:1
        sum_from_row = Float64(0.0)

        #Iteration through columns
        for j = i + 1:min(i + 2 * l + 1, n)
            # Calculates sum of elements in row 
            sum_from_row += _A[_perm[i], j] * x[j]
        end
        # Calculates solution of equation set using calculated sum
        x[i] = (_b[_perm[i]] - sum_from_row) / _A[_perm[i], i]
    end

    # Returns vector with solutions of equations set 
    return x
end

