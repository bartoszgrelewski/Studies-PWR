include("miejsca_zerowe.jl")
using .MiejscaZerowe


function f(x)
    sin(x) - (1/4)x^2
end

function fPrim(x)
    cos(x) - (1/2)x
end

delta = (1/2)*10^(-5)
epsilon = (1/2)*10^(-5)

println(mbisekcji(f, 1.5, 2.0, delta, epsilon))
println(mstycznych(f, fPrim, 1.5, delta, epsilon, 100))
println(msiecznych(f, 1.0, 2.0, delta, epsilon, 100))