--partitions [1,2,3,4,5]

subsets :: [a] -> [[a]]
subsets [] = [[]]
subsets (x:xs) = map (x:) (subsets xs) ++ subsets xs

partitions :: Eq a => [a] -> [([a], [a])]
partitions [] = []
partitions xs = [(a, b) | a <- sub, b <- sub, (a ++ b) == xs]
    where sub = subsets xs

-- z cw robi to samo
partitions' xs = [(take a xs, drop a xs) | a <- [0..length]]