package recursion;

public class SumOfDigits {

	public static void main(String... strings) {
		for (int i = 100; i < 1000; i++) {
			System.out.println("sum of digits "+ i + " = " + sumDigits(i));
		}
	}

	private static int sumDigits(int number) {
		int currentNum = number/10;
		if(currentNum == 0)
			return number;
		
		return sumDigits(currentNum) + number%10;
	}
	
	
}
