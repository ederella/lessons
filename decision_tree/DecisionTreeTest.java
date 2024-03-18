package decisionTree;

import java.util.HashMap;
import java.util.Map.Entry;

public class DecisionTreeTest {
	
	public static void main(String[] string) throws Exception{
		DecisionTreeCalulator tree = new DecisionTreeCalulator();
		
		tree.fill("input.csv");
		
		HashMap<String, Double> gains = tree.calculateGain();

		for (Entry<String, Double> gain : gains.entrySet()) {
			System.out.println(gain.getKey() + " - " + gain.getValue());
		}		
	}

}
