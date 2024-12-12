// 16.1
let notDivisible(m,n) = m % n <> 0

// 16.2
let rec check = function
  |(n,1) -> true
  |(n,m) -> n % m <> 0 && check(n, m-1)

let prime n = check(n, n-1)
