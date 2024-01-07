package recursion;

import java.util.ArrayList;
import java.util.List;

public class EvenIndexPrinter {

	public static void main(String... strings) {

		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < 20; i++) {
			list.add(i+1);
		}

		System.out.println(printEvenIndex(list));
	}

	public static String printEvenIndex(List<Integer> list) {
		return printEvenIndex(list, 0);
	}

	private static String printEvenIndex(List<Integer> list, int currentIndex) {
		if (currentIndex == list.size() - 1)
			return "";

		return getEvenIndex(list, currentIndex)
				+ printEvenIndex(list, currentIndex + 1);
	}

	private static String getEvenIndex(List<Integer> list, int currentIndex) {
		return currentIndex % 2 == 0 ? list.get(currentIndex).toString() + " " : "";
	}
}
