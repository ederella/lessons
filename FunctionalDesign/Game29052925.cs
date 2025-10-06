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
        bool debugMode = true;
        return FindMatches(currentState.Board).Count == 0
                ? currentState
                : currentState
                    .Pipe(bs =>
                    {
                        return RemoveMatches(bs, FindMatches(bs.Board));
                    })
                    .Draw(debugMode)
                    .Pipe(FillEmptySpaces)
                    .Draw(debugMode)
                    .Pipe(ProcessCascade);
}
