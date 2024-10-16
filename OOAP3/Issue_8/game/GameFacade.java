package game;

public interface GameFacade {

	public int startGame();
	public int restartGame();
	public int saveGame();
	public int loadLastGame();
	public int makeStep(String stepCommand);	
	public String displayStatistics();
}
