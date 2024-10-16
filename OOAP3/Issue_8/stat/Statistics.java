package stat;

import bonus.BonusManager;

public class Statistics {
	private PointCounter points;
	private BonusManager bonusManager;
	
	public String displayResults() {
		return "value: "+ points.getValue() 
		+ '\n' +"steps: "+ points.getStepCount()
		+ '\n' + bonusManager.display();
	}

}
