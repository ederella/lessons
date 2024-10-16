package game;


public abstract class GameField {

	Grid<GameCell> cells;
	
	public abstract int init();
	
	public abstract int cleanUp();
	
	public abstract int proceedStep(String pos1, String pos2);
	
}
