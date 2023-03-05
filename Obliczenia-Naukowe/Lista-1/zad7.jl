function f(x)
    return sin(x) + cos(3 * x)
end

function derivative(x0, h)
    return (f(x0 + h) - f(x0)) / h
end

function derivativeF(x)
    return cos(x) - 3 * sin(3 * x)
end

x0 = Float64(1.0)

for i in 0:54
    global h = Float64(2.0 ^ -i)
    ad = derivative(x0, h)
    d = derivativeF(x0)
    println("n = ", i, "    approximate derivative = ", ad, "   derivative = ", d, "    difference = ", d - ad)
end