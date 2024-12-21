// 23.4.1
let (.+.) x y = 
    let (x_gold, x_silver, x_copper) = x
    let (y_gold, y_silver, y_copper) = y
    let copper = x_copper + y_copper
    let silver = x_silver + y_silver + copper/12
    let gold = x_gold + y_gold + silver/20
    (gold, silver%20, copper%12)

let (.-.) x y = 
    let (x_gold, x_silver, x_copper) = x
    let (y_gold, y_silver, y_copper) = y
    let copper_x = x_gold*20*12 + x_silver*12 + x_copper
    let copper_y = y_gold*20*12 + y_silver*12 + y_copper
    let result = copper_x - copper_y
    let gold = result/20/12
    let silver = result%(20*12)/12
    let copper = result - gold*20*12 - silver*12
    (gold, silver, copper)

// 23.4.2
let (.+) x y = 
    let (a, b) = x
    let (c, d) = y
    (a + c, b + d)

let (.-) x y = 
    let (c, d) = y
    let minus_y = (-c,-d)
    x .+ minus_y
 
let (.*) x y = 
    let (a, b) = x
    let (c, d) = y
    (a*c - b*d, b*c + a*d)

let (./) x y = 
    let (a, b) = y
    let div_y = (a/(a*a + b*b),-b/(a*a + b*b))
    x .* y
