
import java.util.*;

public class LinkedList {
	public Node head;
	public Node tail;

	public LinkedList() {
		head = null;
		tail = null;
	}

	public void addInTail(Node item) {
		if (this.head == null)
			this.head = item;
		else
			this.tail.next = item;
		this.tail = item;
	}

	public Node find(int value) {
		Node node = this.head;
		while (node != null) {
			if (node.value == value)
				return node;
			node = node.next;
		}
		return null;
	}

	public ArrayList<Node> findAll(int _value) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		Node node = this.head;
		while (node != null) {
			if (node.value == _value) {
				nodes.add(node);
			}
			node = node.next;
		}
		return nodes;
	}

	public boolean remove(int _value) {
		Node node = this.head;
		if (node != null) {
			if (node.value == _value) {
				this.head = this.head.next;
				if(this.head == null)
					this.tail = null;
				return true;
			}
			while (node.next != null) {
				if (node.next.value == _value) {
					if(node.next == this.tail)
						this.tail = node;
					node.next = node.next.next;
					return true;
				}
				node = node.next;
			}
		}
		return false;

	}

	public void removeAll(int _value) {
		Node tempHead = new Node(0);
		tempHead.next = this.head;
		
		Node node = tempHead;
		while (node != null) {
			if(node.next == null) {
				this.tail = node;
				break;
			}else {
				if(node.next.value == _value) 
					node.next = node.next.next;					
				else 
					node = node.next;
			}
		}
		if(this.tail == tempHead)
			this.tail = null;
			
		this.head = tempHead.next;
	}

	public void clear() {
		head = null;
		tail = null;
	}

	public int count() {
		int count = 0;
		Node node = this.head;
		while (node != null) {
			node = node.next;
			count++;
		}

		return count;
	}

	public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
		if (_nodeToInsert == null)
			return;

		if (_nodeAfter == null) {
			Node newNext = this.head;
			this.head = _nodeToInsert;
			this.head.next = newNext;
			return;
		}

		Node node = this.head;
		while (node != null) {
			if (node.value == _nodeAfter.value) {
				Node newNext = node.next;
				node.next = _nodeToInsert;
				node.next.next = newNext;
				if(node.next.next == null)
					tail = node.next;
				return;
			}
			node = node.next;
		}
	}

}

class Node {
	public int value;
	public Node next;

	public Node(int _value) {
		value = _value;
		next = null;
	}
}