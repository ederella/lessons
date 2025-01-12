// 47.4.1
let f n = 
    let mutable fact = 1
    let mutable x = 1
    while x <= n do
        fact <- fact * x
        x <- x+1
    fact

// 47.4.2
let fibo n = 
    let mutable n0 = 0
    let mutable n1 = 1
    if n = 0 
        then n0
    elif n = 1 
        then n1
    else
        let mutable x = 2        
        while x <= n do
            let tmp = n0
            n0 <- n1
            n1 <- tmp + n0
            x <- x+1
        n1
