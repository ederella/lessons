package sort3;

import java.util.*;

public class SortLevel {
	
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
