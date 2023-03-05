# Bartosz Grelewski 261725

include("blocksys.jl")
include("matrixgen.jl")
using .blocksys
using .matrixgen
using Test
using PyPlot

# Sizes of matrices to generate
mat_sizes = [1000, 2000, 5000, 10000, 20000, 25000, 30000, 50000]
sub_size = 4
cond = Float64(1.0)

# Tests results
times_gauss = Vector{Float64}(undef, length(mat_sizes))
times_gauss_choose = Vector{Float64}(undef,length(mat_sizes))

memory_gauss = Vector{Float64}(undef,length(mat_sizes))
memory_gauss_choose = Vector{Float64}(undef,length(mat_sizes))

operations_gauss = Vector{Float64}(undef,length(mat_sizes))
operations_gauss_choose = Vector{Float64}(undef,length(mat_sizes))

# Generating matrices and solving them using Gauss and Gauss with choose methods
for i = 1:length(mat_sizes)

    # Generating matrix for tests
    blockmat(mat_sizes[i], sub_size, cond, "test_matrix.txt")
    println("Matrix size: ", mat_sizes[i])
    println("Submatrices size: ", sub_size)

    # Loading generated matrix and computing vector of right sides
    matrix = load_matrix("test_matrix.txt")
    vec = create_right_side_vector(matrix[1], matrix[2], matrix[3])

    # Gauss stats array
    results = @timed solve_gauss(matrix[1], vec, matrix[2], matrix[3])
    
    # time and memory usage of gauss method for matrix of size mat_sizes[i]
    times_gauss[i] = results[2] 
    memory_gauss[i] = results[3]
    operations_gauss[i] = mat_sizes[i] * sub_size^2 + 2 * mat_sizes[i] # liczba operacji nl^2(1 czesc)+2n(2 czesc algorytmu)

    # Loading generated matrix and computing vector of right sides
    matrix = load_matrix("test_matrix.txt")
    vec = create_right_side_vector(matrix[1], matrix[2], matrix[3])

    # Gauss with choose
    results = @timed solve_gauss_with_choose(matrix[1], vec, matrix[2], matrix[3])
    
    times_gauss_choose[i] = results[2]
    memory_gauss_choose[i] = results[3]
    operations_gauss_choose[i] = mat_sizes[i] * sub_size^3 + 2 * mat_sizes[i]

end

#### plots section

# Generate plots for times of computing (solving methods)
clf()
plot(mat_sizes, times_gauss, label="Gauss", linewidth=1.0)
plot(mat_sizes, times_gauss_choose, label="Gauss with choose", linewidth=1.0)
grid(true)
legend(loc=2,borderaxespad=0)
title("Times of computing (solving methods)")
savefig("plots/times_solving.png")

# Generate plots for memory usage (solving methods)
clf()
plot(mat_sizes, memory_gauss, label="Gauss", linewidth=1.0)
plot(mat_sizes, memory_gauss_choose, label="Gauss with choose", linewidth=1.0)
grid(true)
legend(loc=2,borderaxespad=0)
title("Used memory (solving methods)")
savefig("plots/memory_solving.png")

# Generate plots for operations (solving methods)
clf()
plot(mat_sizes, operations_gauss, label="Gauss", linewidth=1.0)
plot(mat_sizes, operations_gauss_choose, label="Gauss with choose", linewidth=1.0)
grid(true)
legend(loc=2,borderaxespad=0)
title("Operations")
savefig("plots/operations_solving.png")


#### UNIT Tests for data from files

@testset "16x16" begin
    matrix = load_matrix("dane/Dane16_1_1/A.txt")
    vec = load_vector("dane/Dane16_1_1/b.txt")
    results = ones(16)

    @test isapprox(solve_gauss(matrix[1], vec[1], matrix[2], matrix[3]), results)

    matrix = load_matrix("dane/Dane16_1_1/A.txt")
    vec = load_vector("dane/Dane16_1_1/b.txt")

    @test isapprox(solve_gauss_with_choose(matrix[1], vec[1], matrix[2], matrix[3]),  results)

end

@testset "10kx10k" begin
    matrix = load_matrix("dane/Dane10000_1_1/A.txt")
    vec = load_vector("dane/Dane10000_1_1/b.txt")
    results = ones(10000)

    @test isapprox(solve_gauss(matrix[1], vec[1], matrix[2], matrix[3]), results)

    matrix = load_matrix("dane/Dane10000_1_1/A.txt")
    vec = load_vector("dane/Dane10000_1_1/b.txt")
    @test isapprox(solve_gauss_with_choose(matrix[1], vec[1], matrix[2], matrix[3]),  results)

end

@testset "50kx50k" begin
matrix = load_matrix("dane/Dane50000_1_1/A.txt")
vec = load_vector("dane/Dane50000_1_1/b.txt")
results = ones(50000)

@test isapprox(solve_gauss(matrix[1], vec[1], matrix[2], matrix[3]), results)

matrix = load_matrix("dane/Dane50000_1_1/A.txt")
vec = load_vector("dane/Dane50000_1_1/b.txt")
@test isapprox(solve_gauss_with_choose(matrix[1], vec[1], matrix[2], matrix[3]),  results)

end

