-- zwykle rekurencyjny Fibonacci

fib1 :: Int -> Int
fib1 1 = 1
fib1 2 = 1
fib1 n = fib1(n-1) + fib1(n-2)
