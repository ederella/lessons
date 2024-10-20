package src.game.grid;

import java.util.List;

import org.springframework.stereotype.Component;

import src.game.cell.GameCell;
import src.game.step.Position;
import util.Constants;

@Component
public class SimpleGrid extends Grid<GameCell>{

	private GameCell[][] grid;
	
	protected SimpleGrid(int size) {
		super(size);
		grid = new GameCell[size][size];
	}

	@Override
	public GameCell getElement(Position pos) {
		return grid[pos.getRow()][pos.getColumn()];
	}

	@Override
	public void setElementValue(Position pos, GameCell value) {
		grid[pos.getRow()][pos.getColumn()].setValue(value);
	}

	@Override
	public void swap(Position pos1, Position pos2) {
		GameCell gc1 = grid[pos1.getRow()][pos1.getColumn()];
		grid[pos1.getRow()][pos1.getColumn()]=grid[pos2.getRow()][pos2.getColumn()];
		grid[pos2.getRow()][pos2.getColumn()]=gc1;
		
	}

	@Override
	public void cleanUp(List<Position> posList) {
		for (Position pos : posList) {
			grid[pos.getRow()][pos.getColumn()].clearValue();
		}
	}
	
	@Override
	public void cleanUp() {
		grid = new GameCell[getSize()][getSize()];
	}

	@Override
	public void moveUpperDown(Position pos) {
		Position upperPos = pos.getUpperPosition();
		if(upperPos.getOpStatus() == Constants.OP_STATUS_SUCCESS) {
			swap(pos, upperPos);
			moveUpperDown(upperPos);
		}
	}

	@Override
	public String print() {
		StringBuilder b = new StringBuilder();
		for(int i = 0; i < getSize(); i++) {
			
			for(int j = 0; j < getSize(); j++) {
				b.append(grid[i][j] + " ") ;
			}	
			b.append('\n');
		}
		
		return b.toString();
	}

	@Override
	public void fillRandom() {
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				grid[i][j].setRandomValue();
			}

		}
	}

	@Override
	public List<Position> getCombinations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fillEmpty() {
		// TODO Auto-generated method stub
		
	}


}
