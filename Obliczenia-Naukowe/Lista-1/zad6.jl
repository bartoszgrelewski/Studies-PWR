function f(x)
    return sqrt(x * x + 1) - 1
end

function g(x)
    return x * x / (sqrt(x * x + 1) + 1)
end

for i in 1:10
    println("8^", -i, ": g(x) = ", g(Float64(8.0 ^ -i)), ", f(x) = ", f(Float64(8.0 ^ -i)))
end