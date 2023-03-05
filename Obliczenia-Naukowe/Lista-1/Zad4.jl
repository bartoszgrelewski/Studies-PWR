#Finding the smallest number
function lower(y)
    while Float64(y*(Float64(1.0)/y)) == Float64(1.0) && y < Float64(2.0)
        y = nextfloat(y)
    end
    println("The smallest x which fulfill the equation is: $(y)")
end

function main()
    y = nextfloat(Float64(1.0))
    lower(y)
end

main()