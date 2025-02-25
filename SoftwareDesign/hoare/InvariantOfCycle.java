package hoare;

public class InvariantOfCycle {
	
	//{arr.length > 0} findMax(arr) {result = max(arr)}
	
	//invariant: 
	//0 <= i < arr.length
	//result = max(arr[0...i])
	
	//before cycle: i = 0; result = arr[0] - ok
	//during cycle: i = i + 1, i < arr.length; result = arr[0...arr.length - 1] - ok
	//after cycle: i = arr.length - 1; result = arr[0...arr.length - 1] - ok
	
	public static int findMax(int[]arr) {
		int result = arr[0];
		
		for(int i = 1; i < arr.length; i++) {
			if(result < arr[i]) {
				result = arr[i];
			}
		}		
		return result;
	}
}
