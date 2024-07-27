package sort;

public class BinarySearch {

	int Left;
	int Right;
	private int[] arr;
	private int found;

	public BinarySearch(int[] arr) {
		this.Left = 0;
		this.Right = arr.length - 1;
		this.arr = arr;
	}

	public void Step(int N) {
		int central = (Right + Left) / 2;

		if (N == arr[central]) {
			found = 1;
			return;
		}
		if (N < arr[central]) {
			Right = central - 1;
			found = 0;
		}
		if (N > arr[central]) {
			Left = central + 1;
			found = 0;
		}
		if (Right < Left) {
			found = -1;
			return;
		}

		if (Right - Left <= 1) {
			found = arr[Right] == N || arr[Left] == N ? 1 : -1;
			return;
		}
	}

	public int GetResult() {
		return found;
	}

	public boolean GallopingSearch(int[] arr, int N) {
		if (arr == null || arr.length == 0)
			return false;

		int index = 0;
		int i = 1;
		while (true) {

			if (arr[index] == N)
				return true;

			if (index == arr.length - 1 && arr[index] < N)
				return false;

			if (arr[index] > N) {
				break;
			}

			index = (int) (Math.pow(2, ++i) - 2);

			if (index >= arr.length)
				index = arr.length - 1;
		}
		this.found = 0;
		this.Left = (int) (Math.pow(2, i - 1) - 2) + 1;
		this.Right = index;
		
		while (this.found == 0) {
			Step(N);
		}

		return this.found == 1;
	}
}