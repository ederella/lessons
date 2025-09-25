public static BoardState InitializeGame(int boardSize = 8)
{
    return new BoardState(new Board(boardSize), 0)
        .Pipe(FillEmptySpaces)
        .Pipe(ProcessCascade);
}

public static TOutput Pipe<TInput, TOutput>(this TInput input, Func<TInput, TOutput> transform)
{
        return transform(input);
}
