package recursion;

import java.util.LinkedList;

public class EvenPrinter {

	public static void main(String... strings) {

		LinkedList<Integer> list = new LinkedList<Integer>();

		for (int i = 0; i < 20; i++) {
			list.add(i);
		}

		System.out.println(printEven(list));
	}

	private static String printEven(LinkedList<Integer> list) {
		if (list.isEmpty())
			return "";
		Integer firstElement = list.pop();

		return getEven(firstElement) + printEven(list);
	}

	private static String getEven(Integer number) {
		return (number.intValue() & 1) == 0 ? number.toString() + " " : "";
	}

}
