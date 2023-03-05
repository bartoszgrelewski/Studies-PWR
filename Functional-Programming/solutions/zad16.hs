-- funkcja zwraca liczbe liczb wzglednie pierwszych z n
-- gcd to wbudowana funkcja zwracajaca najwiekszy wspolny dzielnik
phi :: Int -> Int
phi n = length [a | a <- [2..n], gcd a n == 1 ] 

-- zwracanie sumy dla wszystkich dzielnikow n
eulerSuma :: Int -> Int
eulerSuma n = sum [ phi k | k <- [1..n], mod n k == 0 ]
