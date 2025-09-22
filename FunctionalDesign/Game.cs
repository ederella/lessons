//...
public static BoardState ProcessCascade(BoardState currentState, Board board){
    
    var matches = FindMatches(board);
   
   	if (matches == null || matches.Count == 0)
		  return currentState;

    BoardState afterRemovalState = RemoveMatches(currentState, matches)
    
    BoardState finalState = FillEmptySpaces(afterRemovalState);

    return ProcessCascade(finalState, board);
}


//...
