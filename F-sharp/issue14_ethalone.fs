let rec sum (p, xs) =
    match xs with
        | [] -> 0
        | (x :: xs) when p x -> x + sum (p, xs)
        | (x :: xs) -> sum (p, xs)

let rec count (xs, n) =
    match xs with
        | [] -> 0
        | head :: _ when head > n -> 0
        | head :: tail when head = n -> 1 + count (tail, n)
        | head :: tail -> count (tail, n)

let rec insert (xs, n) =
    match xs with
        | [] -> [n]
        | head :: _ when head >= n -> n :: xs
        | head :: tail -> head :: insert (tail, n)

let rec intersect (xs1, xs2) =
    match (xs1, xs2) with
        | ([],_) | (_,[]) -> []
        | (h1::t1, h2::t2) when h1 = h2 -> h1 :: intersect (t1, t2)
        | (h1::t1, h2::t2) when h1 < h2 -> intersect (t1, xs2)
        | (h1::t1, h2::t2) -> intersect (xs1, t2)

let rec plus (xs1, xs2) =
    match (xs1, xs2) with
        | ([],[]) -> []
        | (xs1, []) -> xs1
        | ([], xs2) -> xs2
        | (h1::t1, h2::t2) when h1 = h2 -> h1 :: h2 :: plus (t1, t2)
        | (h1::t1, h2::t2) when h1 < h2 -> h1 :: plus (t1, xs2)
        | (h1::t1, h2::t2) -> h2 :: plus (xs1, t2)

let rec minus (xs1, xs2) =
    match (xs1, xs2) with
        | ([],[]) -> []
        | (xs1, []) -> xs1
        | ([], xs2) -> []
        | (h1::t1, h2::t2) when h1 = h2 -> minus (t1, t2)
        | (h1::t1, h2::t2) when h1 < h2 -> h1 :: minus (t1, xs2)
        | (h1::t1, h2::t2) -> minus (xs1, t2)

let rec smallest (xs : int list) =
    match xs with
        | [] -> None
        | [x] -> Some x
        | x :: tail -> let x2 = Option.get (smallest tail)
                       Some (if x < x2 then x else x2)

let rec delete (n, xs) =
    match xs with
        | [] -> []
        | x :: tail when x = n -> tail
        | x :: tail -> x :: delete (n, tail)

let rec sort = function
    | [] -> []
    | xs -> let s = Option.get (smallest xs)
            s :: sort (delete (s, xs))

let rec revrev = function
    | [] -> []
    | [x] -> [List.rev x]
    | x :: xs -> (revrev xs) @ [List.rev x]
