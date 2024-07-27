package stack;

import java.util.*;

public class Stack<T> {
	class Node {
		T value;
		Node prev;
		Node(T value) {
			this.value = value;
		}
	}
	Node head;
	Node tail;
	private int size;
	
	public Stack() {
		head = null;
		tail = null;
	}

	public int size() {
		return size;
	}

	public T pop() {
		if (size == 0)
			return null;
		T val = tail.value;
		tail = tail.prev;
		size--;
		return val;
	}

	public void push(T val) {
		Node node = new Node(val);
		
		if (this.head == null)
			this.head = node;

		node.prev = tail;
		tail = node;
		size++;
	}

	public T peek() {
		if(size == 0)
			return null;
		else
			return tail.value;
	}
}


