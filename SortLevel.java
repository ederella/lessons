package sort;

import java.util.*;

public class SortLevel {
	public static void SelectionSortStep(int array[], int i) {
		if (i >= array.length - 1)
			return;
		int minIdx = findIndexOfMin(array, i + 1);

		int valueOfMin = array[minIdx];
		array[minIdx] = array[i];
		array[i] = valueOfMin;
	}

	private static int findIndexOfMin(int[] array, int minIdx) {
		for (int i = minIdx + 1; i < array.length; i++) {
			if (array[i] < array[minIdx])
				minIdx = i;
		}
		return minIdx;
	}

	public static boolean BubbleSortStep(int array[]) {
		boolean isSorted = true;
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1]) {
				int iValue = array[i];
				array[i] = array[i + 1];
				array[i + 1] = iValue;
				isSorted = false;
			}
		}
		return isSorted;
	}

	public static void InsertionSortStep(int[] array, int step, int i) {
		for (int j = i + step; j < array.length; j += step) {
			insert(array, step, i, j);
		}
	}

	private static void insert(int[] array, int step, int i, int j) {
		for (int k = i; k < j; k += step) {
			if (array[k] > array[j]) {
				int jValue = array[j];
				move(array, step, k, j);
				array[k] = jValue;
			}
		}
	}

	private static void move(int[] array, int step, int k, int j) {
		for (int i = j; i > k; i -= step) {
			array[i] = array[i - step];
		}
	}

	public static ArrayList<Integer> KnuthSequence(int array_size) {
		LinkedList<Integer> sequence = new LinkedList<Integer>();
		sequence.push(1);

		while (true) {
			int nextElement = 3 * sequence.peek() + 1;
			if (array_size <= nextElement)
				return new ArrayList<Integer>(sequence);
			sequence.push(nextElement);
		}
	}

	public static void sortShell(int[] array) {

		ArrayList<Integer> sequence = KnuthSequence(array.length);

		for (Integer step : sequence) {
			for (int i = 0; i < step; i++) {
				InsertionSortStep(array, step, i);
			}
		}
	}

	public static int ArrayChunk(int[] M) {
		if (M.length == 0)
			return -1;
		int nIndex = M.length / 2;
		int N = M[nIndex];
		int i1 = 0;
		int i2 = M.length - 1;

		while (true) {
			while (M[i1] < N)
				i1++;

			while (M[i2] > N)
				i2--;

			if (i1 == i2 - 1 && M[i1] > M[i2]) {
				swap(M, i1, i2);
				nIndex = M.length / 2;
				N = M[nIndex];
				i1 = 0;
				i2 = M.length - 1;
				continue;
			}
			if (i1 == i2 || (i1 == i2 - 1 && M[i1] < M[i2])) {
				return nIndex;
			}

			nIndex = nIndex == i1 ? i2 : nIndex == i2 ? i1 : nIndex;

			swap(M, i1, i2);
		}
	}

	private static void swap(int[] M, int i1, int i2) {
		int m1 = M[i1];
		M[i1] = M[i2];
		M[i2] = m1;
	}

	public static void QuickSort(int[] array, int left, int right) {
		if (left >= right)
			return;
		int nIndex = ArrayChunk(array, left, right);
		QuickSort(array, left, nIndex - 1);
		QuickSort(array, nIndex + 1, right);
	}

	private static int ArrayChunk(int[] M, int left, int right) {
		if (M.length == 0)
			return -1;
		int nIndex = (right - left + 1) / 2 + left;
		int N = M[nIndex];
		int i1 = left;
		int i2 = right;

		while (true) {
			while (M[i1] < N)
				i1++;

			while (M[i2] > N)
				i2--;

			if (i1 == i2 - 1 && M[i1] > M[i2]) {
				swap(M, i1, i2);
				nIndex = (right - left + 1) / 2 + left;
				N = M[nIndex];
				i1 = left;
				i2 = right;
				continue;
			}
			if (i1 == i2 || (i1 == i2 - 1 && M[i1] < M[i2])) {
				return nIndex;
			}

			nIndex = nIndex == i1 ? i2 : nIndex == i2 ? i1 : nIndex;

			swap(M, i1, i2);
		}
	}
	
	public static void QuickSortTailOptimization(int[] array, int left, int right) {
		while (left < right) {
			int nIndex = ArrayChunk(array, left, right);
			QuickSort(array, left, nIndex - 1);
			left = nIndex + 1;
		}
	}
}
