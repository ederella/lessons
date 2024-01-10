package recursion;

import java.util.ArrayList;
import java.util.List;

public class SecondMax {


	public static void main(String... strings) {

		List<Integer> list = new ArrayList<Integer>();

		list.add(1);
		list.add(90);
		list.add(2);
		list.add(3);
		list.add(4);
		
		System.out.println(getSecondMax(list));		
		
		list.add(1);
		list.add(90);
		list.add(2);
		list.add(3);
		list.add(4);
		
		System.out.println(getSecondMax(list));
	}

	private static int getSecondMax(List<Integer> list) {

		return getSecondMax(list, 0, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}
	
	private static int getSecondMax(List<Integer> list, int startPosition, int firstMax, int secondMax) {
		if(list.size() <= startPosition)
			return secondMax;
		
		if(list.get(startPosition) >= firstMax) {
			secondMax = firstMax;
			firstMax = list.get(startPosition);
		}
		if(list.get(startPosition) < firstMax && list.get(startPosition) > secondMax) {
			secondMax = list.get(startPosition);
		}

		return getSecondMax(list, startPosition + 1, firstMax, secondMax);
	}
}
