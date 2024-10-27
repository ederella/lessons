package src.game.grid;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import src.game.step.Position;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class Grid<T> {

	private int size;

	public abstract T getElement(Position pos);
	
	public abstract void setElementValue(Position pos, T value);
	
	public abstract void swap(Position pos1, Position pos2);

	public abstract void cleanUp(List<Position> posList);
	
	public abstract void cleanUp();
	
	public abstract void moveUpperDown(Position pos);
	
	public abstract String print();
	
	public abstract void fillRandom();

	public abstract List<Position> getCombinations();

	public abstract void fillEmpty();

}
