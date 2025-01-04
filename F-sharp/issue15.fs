// 41.4.1
let list_filter f xs = 
  let ff = fun x y -> if f(x) then x::y else y
  List.foldBack ff xs []

// 41.4.2
let sum p xs = 
  let fp = fun x y -> if p(x) then x + y else y
  List.fold fp 0 xs

// 41.4.3
let revrev = 
  let rev = fun x y -> List.rev(y)::x
  List.fold rev []
