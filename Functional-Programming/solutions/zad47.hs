approxR :: Float -> Float
approxR n = foldr sumInverseR 0 [0 .. n]

approxL :: Float -> Float
approxL n = foldl sumInverseL 0 [0 .. n]

sumInverseR :: Float -> Float -> Float
sumInverseR x y = (1/(product [1 .. x])) + y

sumInverseL :: Float -> Float -> Float
sumInverseL x y = x + (1/(product [1 .. y]))