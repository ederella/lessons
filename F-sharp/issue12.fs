// 34.1
let rec upto x = 
    let rec uptofun = function
        |(0, ls) -> ls
        |(x, ls) -> uptofun(x-1, x::ls)
    uptofun(x, [])

// 34.2
let rec dnto x = 
    let rec dntofun = function
        |(1, ls) -> 1::ls
        |(x, ls) -> x::dntofun(x-1, ls)
    dntofun(x, [])

// 34.3
let rec evenn x = 
    let rec evennfun = function
        |(0, ls) -> 0::ls
        |(x, ls) -> evennfun(x-1, (2*x)::ls)
    evennfun(x, [])
