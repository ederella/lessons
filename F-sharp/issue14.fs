// 40.1
let rec sum = function
  |(_, []) -> 0
  |(p, [x]) -> if p(x) then x else 0
  |(p, head::tail) -> sum(p, [head]) + sum(p, tail)
  
// 40.2.1
let rec count = function
  |([], _) -> 0
  |([x], n) -> if x=n then 1 else 0
  |(head::tail, n) -> if head > n then 0 else count([head], n) + count(tail, n)


// 40.2.2
let rec insert = function
  |([], n) -> [n]
  |(head::tail, n) -> if head > n then n::(head::tail) else head::(insert(tail, n))


// 40.2.3
let rec intersect = function
  |([],_)| (_, []) -> []
  |([x1], [x2]) -> if x1 = x2 then [x1] else []
  |(head1::tail1, head2::tail2) -> 
          if head1 > head2 
            then intersect(head1::tail1, tail2)
          elif head1 < head2 
            then intersect(tail1, head2::tail2)
          else head1::intersect(tail1, tail2)


// 40.2.4
let rec plus = function
  |([], xs2) -> xs2
  |(xs1, []) -> xs1
  |(head1::tail1, head2::tail2) -> 
            if head1 > head2
              then head2::plus(head1::tail1, tail2)
            elif head1 < head2 
              then head1::plus(tail1, head2::tail2)
            else [head1;head2] @ plus(tail1, tail2)

// 40.2.5
let rec minus = function
  |([], _) -> []
  |(xs1, []) -> xs1
  |([x1], [x2]) -> if x1=x2 then [] else [x1]
  |(head1::tail1, head2::tail2) -> 
            if head1 > head2
              then minus(head1::tail1, tail2)
            elif head1 < head2
              then head1::minus(tail1, head2::tail2)
            else minus(tail1, tail2)

let rec min(a,b) = 
    let aa = Option.get(a) 
    let bb = Option.get(b)
    if aa > bb then b else a

// 40.3.1
let rec smallest = function
    |[] -> None
    |[x] -> Some(x)
    |head::tail -> min(Some(head), smallest(tail))

// 40.3.2
let rec delete = function
  |(n, []) -> []
  |(n, [x]) -> if n=x then [] else [x]
  |(n, head1::(head2::tail)) -> 
        if head1 = n then head2::tail
        elif head2 = n then head1::tail
        else [head1;head2] @ delete(n, tail)

// 40.3.3
let rec sort xs=
  if List.length xs > 1 
    then
        let x = Option.get(smallest(xs))
        x::sort(delete(x,xs))
    else xs
  
// 40.4
let rec rev = function
  |[] -> []
  |[x] -> [x]
  |head::tail -> rev(tail) @ [head]
  
let rec revrev = function
  |[] -> []
  |[x] -> [rev(x)]
  |head::tail -> revrev(tail) @ [rev(head)]
