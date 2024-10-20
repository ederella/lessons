package src.bonus.points;


import src.stat.PointCounter;

public class BonusDoublePoints extends BonusPointsStrategy{

	public BonusDoublePoints(PointCounter counter) {
		super(counter);
	}
	
	@Override
	public int recalc(int val) {
		return 2 * val;
	}
	
	@Override
	public void deactivate() {
		super.activate();
	}
}
