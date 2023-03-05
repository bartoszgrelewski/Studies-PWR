
function ilorazyRoznicowe(x::Vector{Float64}, f::Vector{Float64})
    n = length(x)
    fx = zeros(n)
    for i in 1:n
        fx[i] = f[i]
    end
    for j in 2:n
        for i in n:-1:j
            fx[i] = (fx[i] - fx[i-1])/(x[i]-x[i-j+1])
        end
    end
    return fx
end