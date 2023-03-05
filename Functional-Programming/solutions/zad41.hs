countEven :: [Int] -> Int
countEven xs = foldr ((+).fromEnum.even) 0 xs