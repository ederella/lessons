package orderedList;


import java.util.*;

class Node<T> {
	public T value;

	public Node<T> next, prev;

	public Node(T _value) {
		value = _value;
		next = null;
		prev = null;
	}
}

public class OrderedList<T> {
	public Node<T> head, tail;

	private boolean _ascending;

	public OrderedList(boolean asc) {
		head = null;
		tail = null;
		_ascending = asc;
	}

	public int compare(T v1, T v2) {
		if(v1 instanceof Number){
			Number n1 = (Number)v1;
			Number n2 = (Number)v2;
			double diff = n1.doubleValue() - n2.doubleValue();
			double err = 0.0000001d;
			return Math.abs(diff) < err ? 0 : diff > 0d ? 1 : -1;
		}
		if(v1 instanceof String){
			String s1 = ((String)v1).trim();
			String s2 = ((String)v2).trim();
			return s1.compareTo(s2);
		}
		return 0;
	}

	public void add(T value) {
		Node<T> newNode = new Node<T>(value);
		if (head == null && tail == null) {
			head = newNode;
			tail = newNode;
			return;
		}
		if (_ascending && compare(head.value, value) >= 0 || !_ascending && compare(head.value, value) <= 0) {

			newNode.next = head;
			head.prev = newNode;
			head = newNode;
			return;
		}
		if (_ascending && compare(tail.value, value) <= 0 || !_ascending && compare(tail.value, value) >= 0) {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
			return;
		}		
		Node<T> node = head.next;			
		while (node != null) {
			if(_ascending && compare(node.value, value)>0){
				putBefore(node, newNode);
				return;
			}
			if(!_ascending && compare(node.value, value)<0){
				putBefore(node, newNode);
				return;
			}
			node = node.next;
		}			
	}

	private void putBefore(Node<T> node, Node<T> newNode) {
		newNode.next = node;
		newNode.prev = node.prev;
		node.prev.next = newNode;
		node.prev = newNode;
	}

	public Node<T> find(T val) {
		if(head == null && tail == null)
			return null;
		if(compare(head.value, val) == 0){
			return head;
		}
		if(compare(tail.value, val) == 0){
			return tail;
		}
		Node<T> node = head.next;
		while (node.next != null) {
			int compareResult = compare(node.value, val);
			if (_ascending && compareResult > 0 || !_ascending && compareResult < 0)
				return null;
			if (compareResult == 0)
				return node;

			node = node.next;
		}
		return null;
	}

	public void delete(T val) {
		Node<T> node = head;
		while (node != null) {
			int compareResult = compare(node.value, val);
			
			if (_ascending && compareResult > 0 || !_ascending && compareResult < 0)
				return;
			
			if(compareResult == 0){
				if(node == head) {
					head = node.next;
					return;
				}
				if(node == tail) {
					tail = node.prev;
					return;
				}
				node.prev.next = node.next;
				node.next.prev = node.prev;
				return;
				
			}
			node = node.next;
		}
	}

	public void clear(boolean asc) {
		head = null;
		tail = null;
		_ascending = asc;
	}

	public int count() {
		int count = 0;
		Node<T> node = head;
		while (node != null) {
			count++;
			node = node.next;
		}
		return count;
	}

	ArrayList<Node<T>> getAll() {
		ArrayList<Node<T>> r = new ArrayList<Node<T>>();
		Node<T> node = head;
		while (node != null) {
			r.add(node);
			node = node.next;
		}
		return r;
	}
}

