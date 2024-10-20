package src.bonus.points.factory;

import src.bonus.points.BonusDoublePoints;
import src.bonus.points.BonusPointsStrategy;
import src.stat.PointCounter;

public class BonusPointsFactory {

	public static BonusPointsStrategy create(int countOfCells, PointCounter counter) {
		if(countOfCells > 3)
			return new BonusDoublePoints (counter);
		
		return new BonusPointsStrategy(counter); 
	}
}
