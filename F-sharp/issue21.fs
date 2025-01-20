// 50.2.1
let fac_seq = seq {
     let rec fac i a = seq {
          yield a 
          yield! fac (i + 1) (i * a)}
     yield! seq (fac 1 1)} 
 
 // 50.2.2
 let seq_seq = seq{
     let rec gen_seq i = seq {
          yield![- i;i]
          yield! gen_seq(i + 1)}
     yield 0
     yield! gen_seq 1}
