//...
public static BoardState ProcessCascade(BoardState currentState)
{
    BoardState finalState = currentState
      .Pipe(FindAndRefoveMatches)
      .Pipe(FillEmptySpaces);   
  
    return finalState == currentState ? finalState : finalState.Pipe(ProcessCascade);
}

public static BoardState FindAndRefoveMatches(BoardState currentState){
    var matches = FindMatches(currentState.Board);
    return RemoveMatches(currentState, matches);
}

public static TOutput Pipe<TInput, TOutput>(this TInput input, Func<TInput, TOutput> transform)
{
        return transform(input);
}

//....
