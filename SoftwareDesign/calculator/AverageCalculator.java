package calculator;

public class AverageCalculator {

	public float calculateAverage(int[] numbers) {

		double totalSum = 0;
		for (int i = 0; i < numbers.length; i++) {
			totalSum += numbers[i];
		}

		double average = totalSum/ numbers.length;
		
		return (float)average;
	}

}
