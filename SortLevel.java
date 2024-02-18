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
}