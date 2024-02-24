package sort4;

import java.util.*;

public class SortLevel {

	public static int ArrayChunk(int[] M) {
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
				int m1 = M[i1];
				M[i1] = M[i2];
				M[i2] = m1;
				nIndex = M.length / 2;
				N = M[nIndex];
				i1 = 0;
				i2 = M.length - 1;
			}
			if (i1 == i2 || (i1 == i2 - 1 && M[i1] < M[i2])) {
				return nIndex;
			}

			nIndex = nIndex == i1 ? i2 : nIndex == i2 ? i1 : nIndex;

			int m1 = M[i1];
			M[i1] = M[i2];
			M[i2] = m1;
		}

	}
}