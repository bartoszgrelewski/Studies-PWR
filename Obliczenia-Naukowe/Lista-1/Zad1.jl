using Printf
# liczenie macheps
function Macheps_Algorithm(type)
    epsilon = type(1.0)
    while type(1.0 + 0.5*epsilon) > type(1.0)
        epsilon = type(0.5 * epsilon)
    end
    return epsilon
end

function eta(type)
    eta = type(1.0)
    while type(0.5*eta) > 0
        eta = type(0.5 * eta)
    end
    return eta
end

function find_max(type)
    max = type(1.0)

    while !isinf(max * type(2.0))
        max *= type(2.0)
    end
    max *= (type(2.0) - eps(type))
    return max
end

function main()
# 1.1 podpunkt
    for type in [Float16, Float32, Float64]
        println("1. Macheps liczony dla: ", type, " ", Macheps_Algorithm(type))
        println("2. Macheps funkcji eps dla: ", type, " ",eps(type))
    end
    println()

# 1.2 podpunkt
    for type in [Float16, Float32, Float64]
        println("1. ets liczony dla: ", type, " ", eta(type))
        println("2. ets funkcji ets dla: ", type, " ",nextfloat(type(0.0)))
    end
    println()

# 1.3 podpunkt
    for type in [Float16, Float32, Float64]
        println("1. find_max liczony dla: ", type, " ", find_max(type))
        println("2. floatmax funkcji floatmax dla: ", type, " ",floatmax(type))
    end
end


main()