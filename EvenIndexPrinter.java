package recursion;

import java.util.ArrayList;
import java.util.List;

public class EvenIndexPrinter {

	public static void main(String... strings) {

		List<Integer> list = new ArrayList<Integer>();

		for (int i = -7; i < 20; i++) {
			list.add(i);
		}

		printEvenIndex(list, 0);
	}

	private static void printEvenIndex(List<Integer> list, int startPosition) {
		if (list.size() == startPosition)
			return;
		if(startPosition % 2 == 0) {
			System.out.println(list.get(startPosition) + " index " + startPosition);
		}
		printEvenIndex(list, startPosition + 1);
	}
}
