package yyy;

import java.util.*;

public class Deque<T> {

	ArrayList<T> array;

	public Deque() {
		array = new ArrayList<T>();
	}

	public void addFront(T item) {
		array.add(0, item);
	}

	public void addTail(T item) {
		array.add(item);
	}

	public T removeFront() {
		return size() > 0 ? array.remove(0) : null;
	}

	public T removeTail() {
		return size() > 0 ? array.remove(size() - 1) : null;
	}

	public int size() {
		return array.size();
	}
}
