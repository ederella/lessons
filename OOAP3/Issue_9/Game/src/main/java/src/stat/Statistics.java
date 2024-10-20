package src.stat;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import src.bonus.BonusManager;

@Component
@AllArgsConstructor
@Getter
public class Statistics {
	private PointCounter points;
	private BonusManager bonusManager;
	
	public String displayResults() {
		return "value: "+ points.getValue() 
		+ '\n' +"steps: "+ points.getStepCount()
		+ '\n' + bonusManager.display();
	}
	
	public void clear() {
		points.clear();
		bonusManager.clear();
	}

}
