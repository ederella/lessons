package recursion;

import java.util.LinkedList;

public class ListLength {

	public static void main(String... strings) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < 200; i++) {
			list.push(i);
		}

		System.out.println(countLength(list));

	}

	private static int countLength(LinkedList<Integer> list) {
		if (list.isEmpty())
			return 0;
		list.pop();
		return countLength(list) + 1;

	}
}
