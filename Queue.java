package queue;

import java.util.*;

public class Queue<T> {
	private ArrayList<T> array;

	public Queue() {
		array = new ArrayList<T>();
	}

	public void enqueue(T item) {
		array.add(item);
	}

	public T dequeue() {
		if(array.size() == 0)
			return null;
		T item = array.get(0);
		array.remove(0);
		return item;
	}

	public int size() {
		return array.size();
	}
}
