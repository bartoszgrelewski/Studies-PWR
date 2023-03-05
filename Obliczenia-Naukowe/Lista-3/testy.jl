include("miejsca_zerowe.jl")
using .MiejscaZerowe
using Test

@testset "Bisect tests" begin
    @test mbisekcji(x -> x^2 - 1, -10.0, 10.0, 0.01, 0.1)[4] == 1
    @test mbisekcji(x -> x^2 - 1, 0.0, 2.0, 0.01, 0.1)[1] ≈ 1.0 atol=0.1
end

@testset "Newton tests" begin
    @test mstycznych(x -> x^2 - 1, x -> 2x, 2.0, 0.01, 0.1, 1)[4] == 1
    @test mstycznych(x -> x^3 + 3, x -> 3x^2, -5.0, 0.01, 0.1, 100)[1] ≈ -3^(1/3) atol=0.1
end

@testset "Secant tests" begin
    @test msiecznych(x -> x^2 - 1, -10.0, 10.0, 0.1, 0.01, 1)[4] == 1
    @test msiecznych(x -> x^2 - 1, 0.0, 2.0, 0.01, 0.1, 10)[1] ≈ 1 atol=0.1
end