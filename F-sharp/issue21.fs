// 50.2.1
let fac_seq n = seq{let rec fact = function
                      |0|1 -> 1
                      |x -> x*fact(x-1)
                    for i in 0..n do
                      yield fact i}
// 50.2.2
let seq_seq n = seq{ yield 0
                     for i in 1..n do
                       yield i
                       yield -i}
