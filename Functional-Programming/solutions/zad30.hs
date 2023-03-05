-- inits'' [1,2,3,4]
inits'' xs = [take n xs | n <- [0..length xs]]
