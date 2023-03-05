"""
a - dlugosc tablicy z wyliczanymi wspolczynnnikami wielomianu
"""
function naturalna(x::Vector{Float64}, fx::Vector{Float64})
    len = length(x)
    a = Vector{Float64}(len)
    a[len] = fx[len]

    for i = len - 1:-1:1
        a[i] = fx[i] - a[i + 1] * x[i]

        for j = i + 1:len - 1
            a[j] = a[j] - a[j + 1] * x[i]
        end
    end

    return a
end

"""
Zlozonosc O(n^2) jest bardzo latwo wykazac. Mamy dwie 
petle for, ktore wykonuja sie n razy. Wiec maksymalnie mozemy 
wykonac n^2 operacji bo n(zewnetrzna petla) * n(wewnetrzna petla). = n^2
"""