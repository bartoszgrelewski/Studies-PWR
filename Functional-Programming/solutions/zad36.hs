trailing_zeros :: Integer -> Integer
trailing_zeros n
    | divided == 0 = 0
    | otherwise = divided + trailing_zeros divided
    where divided = n `div` 5