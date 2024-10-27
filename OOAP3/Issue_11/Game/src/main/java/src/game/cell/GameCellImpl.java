package src.game.cell;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import lombok.Getter;

public class GameCellImpl implements GameCell{
	@Getter
	private String value;

	@Override
	public void setRandomValue() {
		List<String> givenList = Arrays.asList("A", "B", "C", "D", "E");
	    value = givenList.get(new Random().nextInt(givenList.size()));
	}

	@Override
	public void setValue(GameCell otherCell) {
		value = ((GameCellImpl) otherCell).value;
	}

	@Override
	public void clearValue() {
		value = "";
	}

	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		
		if(o instanceof GameCellImpl) {
			GameCellImpl other = (GameCellImpl)o;
			return value.equals(other.getValue());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
	public String toString() {
		if(value.isEmpty())
			return "*";
		return value;
	}

	@Override
	public boolean isEmpty() {
		return value.isEmpty();
	}
	
}
