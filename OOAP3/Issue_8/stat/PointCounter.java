package stat;

import bonus.BonusPointsStrategy;

public class PointCounter {

	private int value;
	private int stepCount;
	
	BonusPointsStrategy bonusStrategy;

	public void addPoints(int addValue) {
		this.value += (bonusStrategy.recalc(addValue));
		this.stepCount++;
	}
	
	public void setBonusStrategy(BonusPointsStrategy bonusStrategy) {
		this.bonusStrategy = bonusStrategy;
	}

	public int getValue() {
		return value;
	}

	public int getStepCount() {
		return stepCount;
	}
	
}
