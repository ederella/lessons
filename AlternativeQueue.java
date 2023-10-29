package queue;

import java.util.Stack;

public class AlternativeQueue<T> {
	private Stack<T> stack1;
	private Stack<T> stack2;

	public AlternativeQueue() {
		stack1 = new Stack<T>();
		stack2 = new Stack<T>();
	}

	public void enqueue(T item) {
		stack2.clear();
		while (stack1.size() > 0) {
			stack2.push(stack1.pop());
		}
		stack1.push(item);
		while (stack2.size() > 0) {
			stack1.push(stack2.pop());
		}
	}

	public T dequeue() {
		if(stack1.size() == 0)
			return null;
		
		return stack1.pop();
	}

	public int size() {
		return stack1.size();
	}
	
}
