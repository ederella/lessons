package alternativeStack;

import java.util.LinkedList;

public class AlternativeStack<T> {
	
	LinkedList<T> storage;

	public AlternativeStack() {
		storage = new LinkedList<T>();
	}

	public int size() {
		return storage.size();
	}

	public T pop() {
		if (size() == 0)
			return null;
		return storage.pop();
	}

	public void push(T val) {
		storage.push(val);
	}

	public T peek() {
		if(size() == 0)
			return null;		
		return storage.peek();
	}
}
