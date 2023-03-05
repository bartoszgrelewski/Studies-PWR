fib2 :: Int -> Int
fib2 n
 | n == 1 = 1
 | n == 2 = 1
 | n > 2 = fib2 (n-1) + fib2 (n-2)
 | otherwise = 0
