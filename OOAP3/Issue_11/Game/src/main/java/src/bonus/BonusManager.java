package src.bonus;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BonusManager {
	private List<BonusStrategy> bonuses;

	public BonusManager() {
		bonuses = new ArrayList<>();
	}
	
	public void addBonus(BonusStrategy bonus) {
		bonuses.add(bonus);
	}
	
	public String display() {
		String result = "";
		for (BonusStrategy bonusStrategy : bonuses) {
			result += bonusStrategy.toString();
		}
		return result;
	}
	
	public void clear() {
		bonuses = new ArrayList<>();
	}
	
}
