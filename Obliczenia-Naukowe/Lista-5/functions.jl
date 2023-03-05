# Bartosz Grelewski 261725

include("blocksys.jl")
using .blocksys
using Test


# Tests programs to solve equations set using gaussian elimination 
# method with and without choosing the biggest element in column
function test_gauss(input_file_matrix::String = "", input_file_vector::String = "", output_file::String = "",
                     with_choose::Bool = false, calc_vec::Bool = false)
    local matrix
    local vec
    local results
    local gen_vec

    # Checks if file with matrix exists and if it does, loads it to matrix variable
    if input_file_matrix == ""
        error("Can't open file with matrix")
    else
        matrix = load_matrix(input_file_matrix)
    end

    # Checks if file with vector exists and if it does, loads it to vec variable
    if  calc_vec
        vec = load_vector(input_file_vector)
        gen_vec = true
    else
        # Generates vector b from matrix A
        t = create_right_side_vector(matrix[1], matrix[2], matrix[3])
        gen_vec = false
        vec = (t, 0)
    end

    # Solves equations set using gaussian elimination method with and without choosing the biggest element in column
    if with_choose
        # Solves equations set using gaussian elimination method with choosing the biggest element in column
        results = solve_gauss_with_choose(matrix[1], vec[1], matrix[2], matrix[3])
    else
        # Solves equations set using gaussian elimination method without choosing the biggest element in column
        results = solve_gauss(matrix[1], vec[1], matrix[2], matrix[3])
    end
    # Checks if output file exists and if it does, writes results to it
    if output_file != ""
        write_results(output_file, results, matrix[2], gen_vec)
    end

    # Tests if results are correct
    @testset "Test solutions" begin
        # Tests if results are correct with relative tolerance 0.25
        @test isapprox(results, ones(matrix[2]), rtol = 0.25)
    end
end

# Tests programs to solve equations set using gaussian elimination method with choosing the biggest element in column
test_gauss("dane/Dane16_1_1/A.txt", "dane/Dane16_1_1/b.txt", "out.txt", true, true)
