-- funkcja wyznaczająca wszystkie właściwe trójki pit. takie, że a<= 200

pitagoras :: Int -> [(Int, Int, Int)]
pitagoras n = [(a, b, c) | a <- [1..n], b <- [1..a], c <- [1..b], a^2 == b^2 + c^2, gcd b c == 1] 
