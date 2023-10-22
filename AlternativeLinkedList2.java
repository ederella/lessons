package alternativeLinkedList2;

import java.util.ArrayList;

public class AlternativeLinkedList2 {
	public Node head;
	public Node tail;
	

	public AlternativeLinkedList2() {
		head = new Node(0);
		head.setDummy();
		
		tail = new Node(0);
		tail.setDummy();
		
		head.next = tail;
		tail.prev = head;
	}

	public void addInTail(Node _item) {
		_item.prev = this.tail.prev;
		_item.next = this.tail;
		_item.prev.next = _item;
		 this.tail.prev = _item;
	}

	public Node find(int _value) {
		Node node = this.head.next;
		while (node != tail) {
			if (node.value == _value)
				return node;
			node = node.next;
		}
		return null;
	}

	public ArrayList<Node> findAll(int _value) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		Node node = this.head.next;
		while (node != tail) {
			if (node.value == _value)
				nodes.add(node);
			node = node.next;
		}
		return nodes;
	}

	public boolean remove(int _value) {
		Node node = this.head.next;

		while (node != tail) {
			if (node.value == _value) {

				node.prev.next = node.next;

				node.next.prev = node.prev;

				return true;
			}
			node = node.next;
		}

		return false;
	}

	public void removeAll(int _value) {
		Node node = this.head;
		
		while (node != null) {
			if (node.value == _value) {
				node.prev.next = node.next;

				node.next.prev = node.prev;
			}
			node = node.next;
		}
	}

	public void clear() {
		head.next = tail;
		tail.prev = head;
	}

	public int count() {
		int count = 0;
		Node node = this.head.next;
		while (node != this.tail) {
			node = node.next;
			count++;
		}

		return count;
	}

	public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
		if (_nodeToInsert == null)
			return;
		if (_nodeAfter == null) {
			_nodeToInsert.prev = head;
			_nodeToInsert.next = head.next;
			head.next = _nodeToInsert;
			_nodeToInsert.next.prev = _nodeToInsert;
			return;
		}
		Node node = this.head.next;
		while (node != tail) {
			if (node.value == _nodeAfter.value) {
				_nodeToInsert.prev = node;
				_nodeToInsert.next = node.next;
				_nodeToInsert.prev.next = _nodeToInsert;
				_nodeToInsert.next.prev = _nodeToInsert; 

				return;
			}
			node = node.next;
		}
	}
}

class Node {
	public int value;
	public Node next;
	public Node prev;
	boolean isDummy;

	public Node(int _value) {
		value = _value;
		next = null;
		prev = null;		
	}
	
	void setDummy() {
		isDummy = true;
	}
}
