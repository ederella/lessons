public static Func<BoardState, BoardState> BuildGamePipeline(bool debugMode)
        {
            return bs => bs
                    .Draw(debugMode)
                    .Pipe(FillEmptySpaces)
                    .Draw(debugMode)
                    .Pipe(ProcessCascade);
        }

public static BoardState ProcessCascade(BoardState currentState)
{
    return currentState
            .Pipe(state => FindMatches(currentState))
            .Pipe(matches => matches.Count == 0
                ? currentState
                : currentState
                    .Pipe(s => RemoveMatches(s, matches))
                    .Pipe(ApplyGravity)
                    .Pipe(FillEmptySpaces)
                    .Pipe(ProcessCascade));
}
