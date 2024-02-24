package sort4;

import java.util.*;

public class SortLevel {

	public static int ArrayChunk(int[] M) {
		if(M.length == 0)
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
}