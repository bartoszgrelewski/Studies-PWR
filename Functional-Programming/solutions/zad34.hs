-- myZip [1,2,3] [9,8,7]
myZip :: [a] -> [b] -> [(a,b)]
myZip (a:as) (b:bs) = (a,b) : myZip as bs
myZip _      _      = []