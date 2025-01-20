// 50.2.1
let fac_seq = seq{let rec fact = function
                      |0|1 -> 1
                      |x -> x * fact(x-1)
                  let mutable i = 0
                  while true do
                      yield fact i
                      i <- (i + 1)
                  }
 
 // 50.2.2
let seq_seq = seq{ 
                  yield 0
                  let mutable i = 1
                  while true do
                       yield -i
                       yield i
                       i <- (i + 1)
                  }
