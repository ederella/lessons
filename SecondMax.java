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
		Max max = new Max();
		max.add(list.get(0));
		max.add(list.get(1));
		return getSecondMax(list, 2, max);
	}
	
	private static int getSecondMax(List<Integer> list, int startPosition, Max max) {
		if(list.size() <= startPosition)
			return max.getSecondMax();
		max.add(list.get(startPosition));
		return getSecondMax(list, startPosition + 1, max);
	}
}
class Max{
	private int firstMax = Integer.MIN_VALUE;
	private int secondMax = Integer.MIN_VALUE;
	
	void add(int newMax) {
		if(newMax >= firstMax) {
			secondMax = firstMax;
			firstMax = newMax;
		}
		if(newMax < firstMax && newMax > secondMax) {
			secondMax = newMax;
		}
	}

	int getSecondMax() {
		return secondMax;
	}
}
