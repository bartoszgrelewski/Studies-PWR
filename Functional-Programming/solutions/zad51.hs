-- Zadanie 51
f' = \(m, s, n) x -> (m + x, (s + ((n * x - m)^2)/(n * (n + 1))), n + 1)
g' = \(m, s, n) -> (m/n, s/n)
avgvar' [] = (0.0, 0.0)
avgvar' (x:xs) = g' $ foldl f' (x, 0.0, 1) xs
-- Istnieje jeszcze inne (lepsze?) rozwiązanie wykorzystujące fakt że var(X) = E(X^2) - E(X)^2 .
