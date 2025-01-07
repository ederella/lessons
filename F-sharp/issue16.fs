// 42.3
let rec allSubsets n k =
    let rec get = function
        | (e1, 0) -> set [Set.empty]
        | (e1, k) when k = n - e1 + 1 -> set [set [e1 .. n]]
        | (e1, k) -> Set.union (Set.map (fun x -> Set.add e1 x) (get(e1 + 1, k - 1))) (get(e1 + 1, k))
    get(1, k)
