//50.2.1
let fac_seq n= seq{
                  let rec fact = function
                      |0|1 -> 1
                      |x -> x*fact(x-1)
                  for i in 0..n do
                      yield fact i
                  }
 
 //50.2.2
let seq_seq n = seq{
                   for i in 0..n do
                     if i%2 = 0 then yield i/2
                     else yield (-1)* (i + 2)/2
                    }
