package recursion;

import java.util.LinkedList;

public class EvenPrinter {

	public static void main(String... strings) {

		LinkedList<Integer> list = new LinkedList<Integer>();

		for (int i = -20; i < 20; i++) {
			list.add(i);
		}

		System.out.println(printEven(list, 0));
	}

	private static String printEven(LinkedList<Integer> list, int startPosition) {
		if (list.size() == startPosition)
			return "";
		return getEven(list.get(startPosition)) + printEven(list, startPosition + 1);
	}

	private static String getEven(Integer number) {
		return (number.intValue() & 1) == 0 ? number.toString() + " " : "";
	}

}
