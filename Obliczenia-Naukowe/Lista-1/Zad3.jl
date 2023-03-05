function main(a, b, s)
    println("[", a, ", ", b, "]")

    x = Float64(a)

    step = Float64(nextfloat(a) - a)
    print("step = ", s, " - ", step == s, "\n")

    for _ in 1:100
        x = nextfloat(x)
        temp = rsplit(bitstring(x), "")
        for i in 2:12
            #print(temp[i])
        end
        #println()
    end

    x = Float64(b)

    for _ in 1:100
        x = prevfloat(x)
        temp = rsplit(bitstring(x), "")
        for i in 2:12
            #print(temp[i])
        end
        #println()
    end
end

main(0.5, 1.0, 2^-53)
main(1.0, 2.0, 2^-52)
main(2.0, 4.0, 2^-51)
