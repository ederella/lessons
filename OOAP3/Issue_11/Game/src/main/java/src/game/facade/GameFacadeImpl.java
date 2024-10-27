package src.game.facade;

import org.springframework.stereotype.Component;

import src.game.field.GameField;
import src.game.step.GameStep;
import src.stat.History;

@Component
public class GameFacadeImpl implements GameFacade{

	private GameField gameField;
	private History history;
	
	public GameFacadeImpl(GameField gameField,  History history) {
		this.gameField = gameField;
		this.history = history;
	}
	
	@Override
	public int restartGame() {
		history.clear();
		gameField.cleanUp();
		gameField.init();
		
		return 0;
	}

	@Override
	public int makeStep(GameStep step) {
		gameField.proceedStep(step);
		return 0;
	}

	@Override
	public String printStepResults() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String printFinalResult() {
		return gameField.printCells();
	}

	@Override
	public String displayStatistics() {
		
		return gameField.showStat();
	}

	@Override
	public int saveGame() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int loadLastGame() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int exitGame() {
		history.clear();
		gameField.cleanUp();
		
		return 0;
	}

	@Override
	public int startGame() {
		history.clear();
		gameField.cleanUp();
		gameField.init();
		return 0;
	}

	@Override
	public String printGameField() {
		return gameField.printCells();
	}

}
