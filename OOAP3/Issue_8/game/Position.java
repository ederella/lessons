package game;

public class Position {

	private int row;
	private int column;
	
	Position(String pos) {
		if (!pos.matches("^[A-Z]+[1-9]?\\d*$")) {
			row = -1;
			column = -1;
			return;
		}
		for (int i = 0; i < pos.length(); i++) {
			if(pos.charAt(i) <= '9') {
				row = Integer.valueOf(pos.substring(i));
				column = Integer.valueOf(pos.substring(0, i));
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
}
