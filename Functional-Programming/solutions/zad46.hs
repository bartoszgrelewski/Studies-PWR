remDup xs = f xs [] where
  f [] _ = []
  f (x:xs) ys
    | isIn x ys = f xs ys
    | otherwise = x : f xs (x:ys)
 
isIn _ []     = False
isIn x (y:ys) = x==y || isIn x ys
 
main = print $ remDup [1,5,2,1,2,5]