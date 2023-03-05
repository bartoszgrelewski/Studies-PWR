--testRev [1,2,3,4]

reverse_list :: [Int] -> [Int]
reverse_list = \list ->
    case list of
        [] -> []
        x:xs -> reverse_list xs ++ [x]

myinits xs = [take n xs | n <- [0..length xs]]

testRev = \n -> (myinits(reverse_list n))
