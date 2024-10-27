package src.game.field;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Component;

import src.bonus.points.BonusPointsStrategy;
import src.bonus.points.factory.BonusPointsFactory;
import src.game.cell.GameCell;
import src.game.grid.Grid;
import src.game.step.GameStep;
import src.game.step.Position;
import src.stat.Statistics;

@Component
public class GameField {

	private Grid<GameCell> cells;	
	private Statistics statistics;
	private Queue<String> stepScreens;
	
	
	public GameField(Grid<GameCell> cells, Statistics statistics) {
		this.cells = cells;
		this.statistics = statistics;
		this.stepScreens = new LinkedList<>();
	}

	public void init() {
		cells.fillRandom();
		removeCombinations();	
		statistics.clear();
	}

	private void removeCombinations() {
		List<Position> posToRemove = cells.getCombinations();
		if(posToRemove.isEmpty()) {		
			return;
		}
		
		cells.cleanUp(posToRemove);		
		for (Position position : posToRemove) {
			cells.moveUpperDown(position);
		}
		cells.fillEmpty();
		removeCombinations();
	}

	public void cleanUp() {
		cells.cleanUp();
		statistics.clear();
	}
	
	public String showStat() {
		return statistics.displayResults();
	}

	public void proceedStep(GameStep step) {
		this.stepScreens.clear();
		cells.swap(step.getPos1(), step.getPos2());
		proceedAfterStep();		
	}
	
	public String printCells() {
		return cells.print();
	}
	
	public String printStepResults() {
		return Arrays.toString(stepScreens.toArray());
	}
	
	private void proceedAfterStep() {
		List<Position> posToRemove = cells.getCombinations();
		stepScreens.add(cells.print());
		if(posToRemove.isEmpty()) {		
			return;
		}
		BonusPointsStrategy str = BonusPointsFactory.create(posToRemove.size(), statistics.getPoints());
		
		str.activate();
		
		statistics.getPoints().addPoints(posToRemove.size());
		
		cells.cleanUp(posToRemove);
		System.out.println(cells.print());
		
		for (Position position : posToRemove) {
			cells.moveUpperDown(position);
		}
		System.out.println(cells.print());
		cells.fillEmpty();
		System.out.println(cells.print());
		proceedAfterStep();
	}
	
}
