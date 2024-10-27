package src.game.step;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import util.Constants;

@ToString
@AllArgsConstructor

public class Position {

	private int row;
	private int column;
	@Getter
	private int opStatus = Constants.OP_STATUS_NOT_EXEC;
	
	public Position(int i, int j) {
		row = i;
		column = j;
	}
	
	public Position(String pos) {
		if (!pos.matches("^[A-Z]+[1-9]?\\d*$")) {
			row = -1;
			column = -1;
			opStatus = Constants.OP_STATUS_EXEC_ERR;
			return;
		}
		for (int i = 0; i < pos.length(); i++) {
			if(pos.charAt(i) <= '9') {
				row = Integer.valueOf(pos.substring(i))-1;
				column = Integer.valueOf(pos.charAt(i-1)) - Integer.valueOf('A');
				opStatus = Constants.OP_STATUS_SUCCESS;
				return;
			}
		}
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public boolean isNeibour(Position p) {
		return Math.abs(p.getRow() - row) <= 1 && Math.abs(p.getColumn() - column) <= 1;
	}
	
	public Position getUpperPosition() {
		if(row == 0)
			return new Position(-1, -1, Constants.OP_STATUS_EXEC_ERR);
		return new Position(row -1, column, Constants.OP_STATUS_SUCCESS);
	}
	
	public boolean equals(Object o) {
		if(o == null)
			return false;
		Position p = (Position)o;
		return (this.column == p.column && this.row == p.row);
		
	}
	
	public int hashCode() {
		return Integer.valueOf(row).hashCode() + 100 * Integer.valueOf(column).hashCode();
	}
}
