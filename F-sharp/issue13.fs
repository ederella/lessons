// 39.1
let rec rmodd ls= 
    let rec rmoddstep = function
        |(_, [], res) -> res
        |(false, src_h::src_t, res) -> rmoddstep(true, src_t, res)
        |(true, src_h::src_t, res) -> src_h::(rmoddstep(false, src_t, res))
    rmoddstep(false, ls, [])

// 39.2
let rec del_even = function
    |[] -> []
    |[n] -> if n % 2 = 1 then [n] else []
    |ls_head :: ls_tail -> del_even([ls_head]) @ del_even(ls_tail)

// 39.3
let rec multiplicity x xs = 
    let rec mul = function
        |[] -> 0
        |[y] -> if x=y then 1 else 0
        |head::tail -> mul [head] + mul tail
    mul(xs)

// 39.4
let rec split ls = 
    let rec split_step = function
        |([], res) -> res
        |([x], (a, b)) -> (a@[x], b)
        |(head_a::(head_b::tail), (a, b)) -> 
                    split_step(tail, (a @ [head_a], b@ [head_b]))
    split_step(ls, ([],[]))


// 39.5
let rec zip = function
    | ([],[]) -> []
    | (_,[]) | ([],_)->  failwith "lists have unequal sizes"
    | ([x],[y]) -> [(x,y)]
    | (head_x::tail_x, head_y::tail_y) -> 
       [(head_x,head_y)] @ zip(tail_x,tail_y) 
