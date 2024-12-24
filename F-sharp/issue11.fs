type F = 
  | AM
  | PM

type TimeOfDay = { hours : int; minutes : int; f: F }

let (.>.) x y = 
    let getAdditionalHours = function
        |PM -> 12
        |_ -> 0
    let transform (a: TimeOfDay) = a.hours + getAdditionalHours(a.f) * 60 + a.minutes
    (transform x) > (transform y)
