package recursion;

public class NumberDegree {

	public static void main(String... strings) {
		for (int i = 0; i < 10; i++) {
			for(int j = 0; j<10; j++) {
				System.out.println(i +"^" +j + " = " + degreeOf(i, j));
			}
		}
	}

	private static int degreeOf(int n, int m) {
		if (m == 0)
			return 1;
		if (m == 1)
			return n;
		return degreeOf(n, m - 1) * n;
	}

}
