package src.stat;

import org.springframework.stereotype.Component;

import src.bonus.points.BonusPointsStrategy;

@Component
public class PointCounter {

	private int value;
	private int stepCount;
	
	private BonusPointsStrategy bonusStrategy;

	public void addPoints(int addValue) {
		this.value += (bonusStrategy.recalc(addValue));
	}
	
	public void addStep() {
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
	
	public void clear() {
		value = 0;
		stepCount = 0;
	}
	
}
