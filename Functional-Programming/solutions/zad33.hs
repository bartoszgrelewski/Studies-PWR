--nonDec[3,2,1,2,3,4,5,6]
nonDec :: (Ord a) => [a] -> Bool
nonDec []       = True
nonDec [x]      = True
nonDec (x:y:xs) = x <= y && nonDec (y:xs)