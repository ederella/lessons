type TimeOfDay = { hours: int; minutes: int; f: string }

type TimeOfDay24 = {hours:int; minutes: int}

let to24format x = 
    if x.f = "PM"
        then {hours = x.hours + 12; minutes = x.minutes}
    else {hours = x.hours; minutes = x.minutes}

let (.>.) x y = 
    let x24 = to24format x
    let y24 = to24format y
    x24 > y24
