package additionalASD;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ImmQueue<T> implements Iterable<T>{

	private Queue<T> innerQueue;

	public ImmQueue() {
		innerQueue = new LinkedList<T>();
	}

	public ImmQueue<T> enqueue(T value) {
		ImmQueue<T> iq = new ImmQueue<T>();
		innerQueue.forEach(e -> iq.innerQueue.add(e));
		iq.innerQueue.add(value);
		return iq;
	}

	public ImmQueue<T> dequeue() {
		ImmQueue<T> iq = new ImmQueue<T>();
		innerQueue.forEach(e -> iq.innerQueue.add(e));
		iq.innerQueue.poll();
		return iq;
	}

	@Override
	public Iterator<T> iterator() {
		return innerQueue.iterator();
	}
}
