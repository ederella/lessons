package calculator;

public class Main {

	public static void main(String[] args) {

		int[] arr = {1,2,3,4,5,6};
		AverageCalculator calc = new AverageCalculator();
		
		System.out.println(calc.calculateAverage(arr));
		System.out.println(calc.calculateAverage(null));//null pointer exception
		
	}

}
