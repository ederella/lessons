// 41.4.1
let list_filter f xs = 
  let ff = fun x y -> 
    if f(x)
      then x::y 
    else y
  List.foldBack ff xs []

// 41.4.2
let sum p xs = 
  let ff = fun x y ->
    if f(x)
      then x + y 
    else x
  List.foldBack ff xs 0

// 41.4.3
let revrev xs = 
  let ff = fun x y -> y @ [List.rev(x)]
  List.foldBack ff xs []
