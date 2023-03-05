import Data.List --chyba

:{
    notSingleDiag k [] i n = True
    notSingleDiag k (x:xs) i n = (k /= (x + i)) && (k /= (x - i)) && (notSingleDiag k xs (i+1) n)
:}
:{
    notDiag [] n = True
    notDiag (x:xs) n = (notSingleDiag x xs 1 n) && (notDiag xs n)
:}
hetman n = [p | p <- (permutations [1..n]), notDiag p n == True]




reverseY x n = map (\z->(n+1-z)) x
isSimilar x y = (x == (reverseY y n)) || (x == (reverse y)) || (x == reverse (reverseY y n)) where n = (length x)
:{
    isNotInside k [] = True
    isNotInside k (x:xs) = if (isSimilar k x) then False else (isNotInside k xs) 
:}
:{
    nonSimilarHetman [] = []
    nonSimilarHetman (x:xs) = if isNotInside x xs then x:(nonSimilarHetman xs) else nonSimilarHetman xs  
:}

length (nonSimilarHetman (hetman 8))