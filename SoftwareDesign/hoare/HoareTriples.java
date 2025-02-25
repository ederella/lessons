package hoare;

public class HoareTriples {

	public static void main(String[] args) {
		System.out.println(maxOfAbs(5, -100));
	}
	
	public static int maxOfAbs(int a, int b) {
		return max(abs(a), abs(b));
	}
	
	private static int max(int a, int b) {
		return a > b ? a : b;
	}

	private static int abs(int x) {
		return x > 0 ? x : -x;
	}
}
