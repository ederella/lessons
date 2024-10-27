package src.game.grid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import src.game.cell.GameCell;
import src.game.cell.GameCellImpl;
import src.game.step.Position;
import util.Constants;

@Component
public class SimpleGrid extends Grid<GameCell>{

	private GameCell[][] grid; 
	
	protected SimpleGrid() {
		super(8);
		grid = new GameCell[getSize()][getSize()];
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
				b.append(grid[i][j].toString() + " ") ;
			}	
			b.append('\n');
		}
		
		return b.toString();
	}

	@Override
	public void fillRandom() {
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				grid[i][j] = new GameCellImpl();
				grid[i][j].setRandomValue();
			}

		}
	}

	@Override
	public List<Position> getCombinations() {
		Set<Position> positions = searchByRows();
		positions.addAll(searchByColumns());

		List<Position> mainList = new ArrayList<>();
		mainList.addAll(positions);
		mainList.sort(Comparator.comparingInt(Position::getRow));
		return mainList;
	}

	private Set<Position> searchByColumns() {
		Set<Position> positionsCols = new HashSet<>();
		for (int j = 0; j < getSize(); j++) {
			for (int i = 0; i < getSize() - 2; i++) {
				if(grid[i][j].equals(grid[i+1][j]) && grid[i+2][j].equals(grid[i][j])) {
					positionsCols.add(new Position(i,j, Constants.OP_STATUS_SUCCESS));
					positionsCols.add(new Position(i+1,j, Constants.OP_STATUS_SUCCESS));
					positionsCols.add(new Position(i+2,j, Constants.OP_STATUS_SUCCESS));
				}
			}
		}
		return positionsCols;
	}

	private Set<Position> searchByRows() {
		Set<Position> positions = new HashSet<>();
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize() - 2; j++) {
				if(grid[i][j].equals(grid[i][j+1]) && grid[i][j].equals(grid[i][j+2])) {
					positions.add(new Position(i,j, Constants.OP_STATUS_SUCCESS));
					positions.add(new Position(i,j+1, Constants.OP_STATUS_SUCCESS));
					positions.add(new Position(i,j+2, Constants.OP_STATUS_SUCCESS));
				}
			}
		}
		return positions;
	}

	@Override
	public void fillEmpty() {
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (grid[i][j].isEmpty())
					grid[i][j].setRandomValue();
			}
		}
	}

}
