--podpunkt 3 
data Tree a = Leaf a | Inner (Tree a) (Tree a)
 
-- function treeB :: Int -> Tree Int that n>= 0 construct tree depth n
treeB :: Int -> Tree Int
treeB 0 = Leaf 0
treeB n = Inner (treeB (n-1)) (treeB (n-1))

-- function treeB :: Int -> Tree Int that n>= 0 construct tree depth n which left subtrees are Leaf 0 and the deepest Leaf is 1
treeB'' :: Int -> Tree Int
treeB'' 0 = Leaf 1
treeB'' n = Inner (Leaf 0) (treeB'' (n-1)) 

-- function that BinTree a, f:a -> BinTree b where Leaf x are replaced by trees f x
mapTree :: (a -> Tree b) -> Tree a -> Tree b
mapTree f (Leaf x) = f x
mapTree f (Inner l r) = Inner (mapTree f l) (mapTree f r)


