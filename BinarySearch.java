package sort;

public class BinarySearch {

	int Left;
	int Right;
	private int[]arr;
	private int found;
	
	public BinarySearch(int[] arr) {
		this.Left = 0;
		this.Right = arr.length - 1;
		this.arr = arr;
	}
	
	public void Step(int N) {
		int central = (Right+Left) / 2;
		if(N == arr[central]) {
			found = 1;
			return;
		}
		
		if(Right - Left <=0) {
			found = -1;
			return;
		}
		if(N < arr[central]) 
			Right = central - 1;
		
		if(N > arr[central]) 
			Left= central + 1;
		
		Step(N);
		
	}
	
	public int GetResult() {
		return found;
	}
	
}
