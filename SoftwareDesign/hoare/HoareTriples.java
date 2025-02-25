package hoare;

public class HoareTriples {
	
	public static void main(String[] args) {
		System.out.println(maxOfAbs(5, -100));
	}

	/**
 	{P: true} 
  	int maxAbs(int a, int b) 
   	{Q: result = max(|a|, |b|)}
 	*/
	public static int maxOfAbs(int a, int b) {
		return max(abs(a), abs(b));
	}

	/**
	{P: true} 
 	int max(int a, int b) 
	{Q: ((result = a) or (result = b)) and (result >= a and result >= b)}
	 */
	
	private static int max(int a, int b) {
		return a > b ? a : b;
	}
	/**
 	{P: true} 
  	int abs(int x) 
   	{Q: result = |x|}
	*/
	private static int abs(int x) {
		return x > 0 ? x : -x;
	}
}
