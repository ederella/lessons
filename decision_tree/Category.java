package decisionTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Category {

	private ArrayList<String> keys;

	private String name;

	public Category(String name) {
		this.keys = new ArrayList<String>();
		this.name = name;
	}

	public HashMap<String, Integer> getValueCounts() {
		HashMap<String, Integer> valueCounts = new HashMap<String, Integer>();

		for (String key : keys) {
			if (!valueCounts.containsKey(key))
				valueCounts.put(key, 0);
			valueCounts.put(key, valueCounts.get(key) + 1);
		}
		return valueCounts;
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getKeys() {
		ArrayList<String> result = new ArrayList<String>();
		result.addAll(result);
		return result;
	}

	public void addKey(String key) {
		keys.add(key);
	}

	public double calculateEntropy() {
		HashMap<String, Integer> valueCounts = getValueCounts();

		return calculateEntropy(valueCounts);
	}

	private double calculateEntropy(HashMap<String, Integer> valueCounts) {
		double entropy = 0;
		if (valueCounts.size() == 1)
			return entropy;

		int totalValues = 0;
		for (Map.Entry<String, Integer> valueCount : valueCounts.entrySet()) {
			totalValues += valueCount.getValue();
		}

		double log2Count = log2(totalValues);

		for (Map.Entry<String, Integer> valueCount : valueCounts.entrySet()) {
			entropy += ((double) valueCount.getValue() / (double) totalValues) * (log2(valueCount.getValue()) - log2Count);
		}
		return -entropy;
	}

	private double log2(int value) {
		return Math.log(value) / Math.log(2);

	}

	public double calculateGain(Category parentCategory) {
		HashMap<String, HashMap<String, Integer>> groupCategories = new HashMap<String, HashMap<String, Integer>>();

		for (int i = 0; i < keys.size(); i++) {

			if (!groupCategories.containsKey(keys.get(i)))
				groupCategories.put(keys.get(i), new HashMap<String, Integer>());

			if (!groupCategories.get(keys.get(i)).containsKey(parentCategory.keys.get(i))) {
				groupCategories.get(keys.get(i)).put(parentCategory.keys.get(i), 0);
			}
			groupCategories.get(keys.get(i)).put(parentCategory.keys.get(i), groupCategories.get(keys.get(i)).get(parentCategory.keys.get(i)) + 1);
		}

		double childEntropy = 0;


		
		for (Map.Entry<String, HashMap<String, Integer>> groupCategory : groupCategories.entrySet()) {
			int totalValues = 0;
			for (Map.Entry<String, Integer> valueCount : groupCategory.getValue().entrySet()) {
				totalValues += valueCount.getValue();
			}
			childEntropy += calculateEntropy(groupCategory.getValue()) * ((double) totalValues / (double) keys.size());
		}

		return parentCategory.calculateEntropy() - childEntropy;
	}
}
