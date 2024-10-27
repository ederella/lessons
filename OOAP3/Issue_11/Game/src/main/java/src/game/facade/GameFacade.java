package src.game.facade;

import src.game.step.GameStep;


public interface GameFacade {

	public int startGame();
	public int restartGame();
	public int makeStep(GameStep step);	
	public String printStepResults();
	public String printFinalResult();
	public String printGameField();
	public String displayStatistics();
	public int exitGame();
	public int saveGame();
	public int loadLastGame();
}
