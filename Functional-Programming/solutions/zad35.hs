--usuwa liczbe z listy
remove_from_list :: Eq a => a -> [a] -> [a]
remove_from_list d xs = [x | x<-xs, x /= d]

permutations :: Eq a => [a] -> [[a]]
permutations [] = [[]]
permutations xs = [y:ys | y<-xs, ys<-permutations (remove_from_list y xs)]