package alternativeStack;


public class AlternativeStack<T> {
	class Node {
		T value;
		Node next;
		Node(T value) {
			this.value = value;
		}
	}
	Node head;

	private int size;
	
	public AlternativeStack() {
		head = null;
	}

	public int size() {
		return size;
	}

	public T pop() {
		if (size == 0)
			return null;
		T val = head.value;
		head = head.next;
		size--;
		return val;
	}

	public void push(T val) {
		Node node = new Node(val);
		
		if (this.head == null)
			this.head = node;

		node.next = head;
		head = node;
		size++;
	}

	public T peek() {
		if(size == 0)
			return null;
		else
			return head.value;
	}
}
