include("miejsca_zerowe.jl")
using .MiejscaZerowe

function f1(x)
    exp(1-x) - 1
end

function f1Prim(x)
    -exp(1-x)
end

function f2(x)
    x*exp(-x)
end

function f2Prim(x)
    -exp(-x)*(x-1)
end

delta = 10^(-5)
epsilon = 10^(-5)

println(mbisekcji(f1, 0.5, 2.0, delta, epsilon))
println(mbisekcji(f2, -1.5, 0.5, delta, epsilon))

println(mstycznych(f1, f1Prim, 0.0, delta, epsilon, 100))
println(mstycznych(f2, f2Prim, -1.0, delta, epsilon, 100))

println(msiecznych(f1, 0.5, 2.0, delta, epsilon, 100))
println(msiecznych(f2, -1.5, 0.5, delta, epsilon, 100))

println(mstycznych(f1, f1Prim, 100.0, delta, epsilon, 100))
println(mstycznych(f2, f2Prim, 100.0, delta, epsilon, 100))
println(mstycznych(f2, f2Prim, 1.0, delta, epsilon, 100))