package trees;

import java.util.Arrays;

public class AlgorithmsDataStructures2 {

	public static int[] GenerateBBSTArray(int[] a) {

		if (a == null || a.length == 0)
			return new int[0];

		int[] sortedA = Arrays.copyOf(a, a.length);
		Arrays.sort(sortedA);

		int[] bstA = new int[a.length];

		fillBBSTArray(0, bstA, 0, sortedA.length, sortedA);

		return bstA;
	}

	private static void fillBBSTArray(int toPos, int[] bstA, int startBorder, int finishBorder, int[] sortedA) {
		if (startBorder > finishBorder)
			return;
		if (toPos >= bstA.length)
			return;

		int fromPos = (finishBorder - startBorder) / 2 + startBorder;

		bstA[toPos] = sortedA[fromPos];

		fillBBSTArray(toPos * 2 + 1, bstA, startBorder, fromPos, sortedA);

		fillBBSTArray(toPos * 2 + 2, bstA, fromPos, finishBorder, sortedA);
	}
}
