package sort1;

import java.util.*;

public class SortLevel {
	public static void SelectionSortStep(int array[], int i) {
		if(i >= array.length - 1)
			return;
		int minIdx = findIndexOfMin(array, i + 1);
		
		int valueOfMin = array[minIdx];		
		array[minIdx] = array[i];
		array[i] = valueOfMin;
	}

	private static int findIndexOfMin(int[] array, int minIdx) {
		for (int i = minIdx + 1; i < array.length; i++) {
			if(array[i] < array[minIdx])
				minIdx = i;
		}
		return minIdx;
	}

	public static boolean BubbleSortStep(int array[]) {
		boolean isSorted = true;
		for (int i = 0; i < array.length-1; i++) {
			if(array[i] > array[i+1]) {
				int iValue = array[i];
				array[i] = array[i+1];
				array[i+1] = iValue;
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
}
