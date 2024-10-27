package src.ui;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import src.game.facade.GameFacade;
import src.game.step.GameStep;
import util.Constants;

@ShellComponent
public class ConsoleManager {

	GameFacade gameFacade;
	
	
	public ConsoleManager(GameFacade gameFacade) {
		this.gameFacade = gameFacade;
	}
	
	@ShellMethod(key ="m", value="game step")
	public String makeStep(String step) {
		GameStep gameStep = new GameStep(step);
		if(gameStep.isAllowed()) {
			gameFacade.makeStep(gameStep);			
			return gameFacade.printFinalResult();
		}
		return "Incorrect input";
	}
	
	@ShellMethod(key ="s", value="game statistics")
	public String printStatistics() {	
		return gameFacade.displayStatistics();
	}
	
	@ShellMethod(key ="g", value="start")
	public String startGame(){
		gameFacade.startGame();	
		return gameFacade.printGameField();
	}
	
	@ShellMethod(key ="r", value="restart")
	public String restartGame(){
		gameFacade.restartGame();
		return gameFacade.printGameField();
	}
	
	@ShellMethod(key="e", value="exit game")
	public String exitGame() {
		gameFacade.exitGame();
		return "ok";
	}
	
	
}
