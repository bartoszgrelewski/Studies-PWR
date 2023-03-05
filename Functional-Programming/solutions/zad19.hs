-- funkcja n po k 2 dane integer i zwraca integer

newtonRec :: Integer -> Integer -> Integer
newtonRec n k
 | k == 0 || k == n = 1
 | n > k = newtonRec (n-1) (k-1) + newtonRec (n-1) k
 | otherwise = error "k > n"
