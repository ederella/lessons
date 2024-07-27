package recursion;

import java.util.ArrayList;
import java.util.List;

public class EvenPrinter {

	public static void main(String... strings) {

		List<Integer> list = new ArrayList<Integer>();

		for (int i = -20; i < 20; i++) {
			list.add(i);
		}
		printEven(list, 0);
	}

	private static void printEven(List<Integer> list, int startPosition) {
		if (list.size() == startPosition)
			return;
		
		if(list.get(startPosition) % 2 == 0) {
			System.out.println(list.get(startPosition));
		}				
		printEven(list, startPosition + 1);
	}
}
