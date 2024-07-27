package additionalASD;

import java.util.Stack;

public class DynMaxStack<T extends Comparable<T>> {
	private Stack<T> stack;
	private Stack<T> maximums;

	public DynMaxStack() {
		stack = new Stack<T>();
		maximums = new Stack<T>();
	}

	public void push(T value) {
		T currentMax = defineMax(value);
		maximums.push(currentMax);
		stack.push(value);
	}

	public T pop() {
		maximums.pop();
		return stack.pop();
	}
	
	private T defineMax(T value) {
		if (maximums.isEmpty())
			return value;

		if (maximums.peek().compareTo(value) > 0)
			return maximums.peek();

		return value;
	}
	
	public T max() {
		if(maximums.isEmpty())
			return null;
		return maximums.peek();
	}
	public boolean isEmpty() {
		return stack.isEmpty();
	}
}
