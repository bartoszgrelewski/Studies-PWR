include("miejsca_zerowe.jl")
using .MiejscaZerowe

println("miejsca przecięcia się 3x i e^x:")
println(mbisekcji(x->(3 * x) - MathConstants.e^x, 0.0, 1.0, 10^-4, 10^-4)[1])
println(mbisekcji(x->(3 * x) - MathConstants.e^x, 2.0, 1.0, 10^-4, 10^-4)[1])