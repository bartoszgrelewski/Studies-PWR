function kahan(type)
    type(3.0) * (type(4.0/3.0) - type(1.0)) - type(1.0)
end

function main()
    for type in [Float16, Float32, Float64]
        println(type)
        println("kahan: ", kahan(type))
        println("eps: ", eps(type))
    end
end

main()