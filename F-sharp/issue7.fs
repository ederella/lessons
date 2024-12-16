// 20.3.1
let vat n x =  float x * (1.0 + (float n)/100.0)

// 20.3.2
let unvat n x = float x / (1.0 + (float n)/100.0)

// 20.3.3
let rec min f = 
    let rec getmin n =
        if f(n) = 0 then n
        else getmin(n+1)
    getmin 1
