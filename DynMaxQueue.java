package additionalASD;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class DynMaxQueue<T extends Comparable<T>> {
	private Queue<T> queue;
	private PriorityQueue<T> maximums;

	public DynMaxQueue() {
		queue = new LinkedList<T>();
		maximums = new PriorityQueue<T>((T e1, T e2) -> e2.compareTo(e1));
	}

	public void enqueue(T value) {
		maximums.add(value);
		queue.add(value);
	}

	public T dequeue() {
		T value = queue.poll();
		maximums.remove(value);
		return value;
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

	public T max() {
		return maximums.peek();
	}
}
