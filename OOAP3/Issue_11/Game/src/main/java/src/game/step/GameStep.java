package src.game.step;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import util.Constants;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

public class GameStep {
	
	private Position pos1;
	private Position pos2;
	private int opStatus;

	public GameStep (String step) {
		opStatus = Constants.OP_STATUS_NOT_EXEC;
		if (!step.matches("^[A-Z]+[1-9]?\\d* [A-Z]+[1-9]?\\d*$")) {
			opStatus = Constants.OP_STATUS_EXEC_ERR;
			return;
		}
		String[] ar = step.split(" ");
		
		pos1 = new Position(ar[0]);
		pos2 = new Position(ar[1]);
		
		opStatus = (pos1.getOpStatus() == Constants.OP_STATUS_SUCCESS
				&& pos2.getOpStatus() == Constants.OP_STATUS_SUCCESS) ? Constants.OP_STATUS_SUCCESS
						: Constants.OP_STATUS_EXEC_ERR;

	}
	
	public boolean isAllowed() {
		return opStatus == Constants.OP_STATUS_SUCCESS && pos1.isNeibour(pos2);
	}
}
