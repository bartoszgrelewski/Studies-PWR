import Data.List


-- Zadanie 50
myTakeWhile :: (a -> Bool) -> [a] -> [a]
myTakeWhile _ [] = []
myTakeWhile f (x:xs) = if (f x) then x : (myTakeWhile f xs) else []

myDropWhile :: (a -> Bool) -> [a] -> [a]
myDropWhile _ [] = []
myDropWhile f (x:xs) = if (f x) then myDropWhile f xs else x:xs


-- Zadanie 51
f' = \(m, s, n) x -> (m + x, (s + ((n * x - m)^2)/(n * (n + 1))), n + 1)
g' = \(m, s, n) -> (m/n, s/n)
avgvar' [] = (0.0, 0.0)
avgvar' (x:xs) = g' $ foldl f' (x, 0.0, 1) xs
-- Istnieje jeszcze inne (lepsze?) rozwiązanie wykorzystujące fakt że var(X) = E(X^2) - E(X)^2 .


-- Zadanie 61
-- wersja moja
findQueens n = [perm | perm <- permutations [1..n], nodiagonalcaptures perm]
    where nodiagonalcaptures [] = True
          nodiagonalcaptures (x:xs) = (diagonalcheck x xs 1) && (nodiagonalcaptures xs)
          diagonalcheck _ [] _ = True
          diagonalcheck e (y:ys) d =  (y /= e + d) && (y /= e - d) && (diagonalcheck e ys (d+1))

hetmani = findQueens 8


-- Zadanie 62
-- wersja moja
mysqrt :: Double -> Double
mysqrt n = let checkSeq a x = if x == g then x else checkSeq a g
                  where g = (x + (a/x))/2
           in checkSeq n n


-- Zadanie 63
-- Wojciech Mukla
combinationNumber n xs = d a b
  where (a, b) = (n, (length xs - 1))
        xs' = listArray (0, b) xs
        d i 0
          | i `mod` (xs' ! 0) == 0 = 1
          | otherwise = 0
        d 0 j = 1
        d i j
          | i - (xs' ! j) >= 0 = d (i - (xs' ! j)) j + d i (j - 1)
          | otherwise = d i (j - 1)


