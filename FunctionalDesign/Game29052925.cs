Func<BoardState, BoardState> ProcessCascade = currentState => 
				FindMatches(currentState.Board).Count == 0
                ? currentState
                : currentState
                    .Pipe(bs =>
                    {
                        return RemoveMatches(bs, FindMatches(bs.Board));
                    })
                    .Draw(debugMode)
                    .Pipe(FillEmptySpaces)
                    .Draw(debugMode)
