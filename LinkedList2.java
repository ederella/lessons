package linkedList2;

import java.util.*;


public class LinkedList2 {
	public Node head;
	public Node tail;

	public LinkedList2() {
		head = null;
		tail = null;
	}

	public void addInTail(Node _item) {
		if (head == null) {
			this.head = _item;
			this.head.next = null;
			this.head.prev = null;
		} else {
			this.tail.next = _item;
			_item.prev = tail;
		}
		this.tail = _item;
	}

	public Node find(int _value) {
		Node node = this.head;
		while (node != null) {
			if (node.value == _value)
				return node;
			node = node.next;
		}
		return null;
	}

	public ArrayList<Node> findAll(int _value) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		Node node = this.head;
		while (node != null) {
			if (node.value == _value)
				nodes.add(node);
			node = node.next;
		}
		return nodes;
	}

	public boolean remove(int _value) {
		Node node = this.head;

		while (node != null) {
			if (node.value == _value) {
				if (node.prev == null) {
					this.head = node.next;
					if (this.head != null)
						this.head.prev = null;
				} else {
					node.prev.next = node.next;
				}
				if (node.next == null) {
					this.tail = node.prev;
					if (node.prev != null)
						node.prev.next = null;
				} else {
					node.next.prev = node.prev;
				}

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
				if(node.prev == null) {
					this.head = node.next;
					if(this.head!= null)
						this.head.prev = null;
				}else {
					node.prev.next = node.next;
				}
				if (node.next == null) {
					this.tail = node.prev;
					if(node.prev!=null)
						node.prev.next = null;
				}else {
					node.next.prev = node.prev;
				}
			}
			node = node.next;
		}
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
			_nodeToInsert.next = this.head;
			if(this.head!=null)
				this.head.prev = _nodeToInsert;
			this.head = _nodeToInsert;
			if(this.head.next == null)
				tail = this.head;
			return;
		}

		Node node = this.head;
		while (node != null) {
			if (node.value == _nodeAfter.value) {
				_nodeToInsert.prev = node;
				_nodeToInsert.next = node.next;
				node.next = _nodeToInsert;

				if(_nodeToInsert.next == null)
					tail = _nodeToInsert;
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
