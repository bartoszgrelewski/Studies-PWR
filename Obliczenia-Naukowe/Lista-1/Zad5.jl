
function forward(x, y, type)
    sum = type(0.0)
    for i in 1:length(x)
        sum += x[i] * y[i]
    end 
    return sum
end

function backward(x, y, type)
    sum = type(0.0)
    #tzn. ze odejmujemy 1 co iteracje wiec iterujemy
    #do 1 ale odejmujac 1 od dlugosci x 
    for i in length(x):-1:1
        sum += x[i] * y[i]
    end 
    return sum
end

function partial_desc(x, y)
    #mnozenie tablic 
    p = x .* y

    sumOfPositiveNumbers = sum(sort(filter(a -> a>0, p), rev=true))
    sumOfNegativeNumbers = sum(sort(filter(a -> a<0, p)))
    return sumOfPositiveNumbers+sumOfNegativeNumbers
end

function partial_asc(x, y)
    #mnozenie tablic
    p = x .* y

    #bierzemy nasz iloczyn p, wyrzucamy mniejsze od zera liczby
    #nastepnie sortujemy i na koncu dodajemy wszystkie do siebie
    sumOfPositiveNumbers = sum(sort(filter(a -> a>0, p)))

    #to samo co wyzej tylko, ze na odwrot przy sortowaniu no i dodajemy 
    #tylko ujemne liczby
    sumOfNegativeNumbers = sum(sort(filter(a -> a<0, p), rev=true))

    return sumOfPositiveNumbers+sumOfNegativeNumbers
end

function main()
x = [2.718281828, -3.141592654, 1.414213562, 0.5772156649, 0.3010299957]
y = [1486.2497, 878366.9879, -22.37492, 4773714.647, 0.000185049]

for type in [Float32, Float64]
    convertedArrayX = Array{type,1}(x)
    convertedArrayY = Array{type,1}(y)
    println(type)
    println("Wartosc iloczynu skalarnego: ", "-1.00657107000000e-11")
    println("forward: ", forward(convertedArrayX, convertedArrayY, type))
    println("backward: ", backward(convertedArrayX, convertedArrayY, type))
    println("partial_desc: ", partial_desc(convertedArrayX, convertedArrayY))
    println("partial_asc: ", partial_asc(convertedArrayX, convertedArrayY))
end

end

main()