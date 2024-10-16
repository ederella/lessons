package game;

import java.util.List;

public abstract class Grid<T> {
	

	private int size;
	
	
	protected Grid(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}


	public abstract T getElement(Position pos);
	
	public abstract int setElementValue(Position pos, T value);
	
	public abstract int swap(Position pos1, Position pos2);

	public abstract int cleanUp(List<Position> posList);
	
	public abstract int moveUpperDown(Position pos);
	
	public abstract String print();
	
}
