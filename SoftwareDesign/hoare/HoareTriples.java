package hoare;

public class HoareTriples {

	/**
	 P{a = n, b = m} maxOfAbs(a,b) Q{res = max(abs(n), abs(m)}
	 P{a = n, b = m} max(a, b) Q{res = если n > m то n иначе m}
	 P{a = n} abs(a) Q{res = если n > 0 то n иначе -n}
	 */
	
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
