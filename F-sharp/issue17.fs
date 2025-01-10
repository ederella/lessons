// 43.3
let try_find key m = 
    let rec find_in_list = function
        |[] -> None
        |head::tail -> 
                let (x,y) = head 
                if x = key 
                    then Some(y) 
                else find_in_list tail
    find_in_list(Map.toList m)
