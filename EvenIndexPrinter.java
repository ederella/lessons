package recursion;

import java.util.ArrayList;
import java.util.List;

public class EvenIndexPrinter {

	public static void main(String... strings) {

		List<Integer> list = new ArrayList<Integer>();

		for (int i = -7; i < 20; i++) {
			list.add(i);
		}

		System.out.println(printEvenIndex(list, 0));
	}

	private static String printEvenIndex(List<Integer> list, int startPosition) {
		if (list.size() == startPosition)
			return "";

		return (startPosition % 2 == 0 ? list.get(startPosition).toString() + " " : "")
				+ printEvenIndex(list, startPosition + 1);
	}
}
