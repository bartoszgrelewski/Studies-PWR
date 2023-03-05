include("blocksys.jl")
include("matrixgen.jl")
using .blocksys
using .matrixgen


function main()
# test reading from a file
    matrix = load_matrix("dane/Dane16_1_1/A.txt")
    vec = load_vector("dane/Dane8_1_1/b.txt")
    println(matrix)

    println(vec)
    println("")

end

main()