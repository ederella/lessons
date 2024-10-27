package src.bonus.points;

import src.bonus.BonusStrategy;
import src.stat.PointCounter;

public class BonusPointsStrategy implements BonusStrategy{

	private PointCounter counter;	
	
	public BonusPointsStrategy(PointCounter counter) {
		this.counter = counter;
	}
	@Override
	public void activate() {
		counter.setBonusStrategy(this);
	}
	public int recalc(int val) {
		return val;
	}
	@Override
	public void deactivate() {
	}
}
