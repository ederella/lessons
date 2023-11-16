package alternativeLinkedList2;

import java.util.ArrayList;

public class AlternativeLinkedList2 {
	public Node head;
	

	public AlternativeLinkedList2() {
		head = new DummyNode();
		head.prev = head;
		head.next = head;	
	}

	public void add(Node _item) {
		_item.prev = head.prev;
		_item.next = head;
		_item.prev.next = _item;
		head.prev = _item;
	}

	public Node find(int _value) {
		Node node = head.next;
		while (!(node instanceof DummyNode)) {
			if (node.value == _value)
				return node;
			node = node.next;
		}
		return null;
	}

	public ArrayList<Node> findAll(int _value) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		Node node = this.head.next;
		while (!(node instanceof DummyNode)) {
			if (node.value == _value)
				nodes.add(node);
			node = node.next;
		}
		return nodes;
	}

	public boolean remove(int _value) {
		Node node = this.head.next;

		while (!(node instanceof DummyNode)) {
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
		Node node = this.head.next;
		
		while (!(node instanceof DummyNode)) {
			if (node.value == _value) {
				node.prev.next = node.next;
				node.next.prev = node.prev;
			}
			node = node.next;
		}
	}

	public void clear() {
		head.prev = head;
		head.next = head;
	}

	public int count() {
		int count = 0;
		Node node = this.head.next;
		while (!(node instanceof DummyNode)) {
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
		while (!(node instanceof DummyNode)) {
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

	public Node(int _value) {
		value = _value;
		next = null;
		prev = null;		
	}
}

class DummyNode extends Node{
	public DummyNode() {
		super(0);
	}	
}