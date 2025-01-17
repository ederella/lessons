// 49.5.1
let even_seq = Seq.initInfinite(fun x -> 2 * x + 2)

// 49.5.2
let fac_seq = Seq.initInfinite(let rec fac x = 
                                  match x with
                                  |0 | 1 ->1
                                  |x -> x + fac(x-1)
                               fun x -> fac x)



// 49.5.3
let seq_seq = Seq.initInfinite(let signed x =if x%2 = 0 then x else -x
                               let rec f_seq x =
                                   match x with
                                   |0 -> 0
                                   |x-> signed(x) + f_seq(x-1)
                               fun x-> f_seq x)
