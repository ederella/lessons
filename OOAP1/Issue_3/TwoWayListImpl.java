package ooap1.list;

public class TwoWayListImpl<T> extends TwoWayList<T>{

	public Node2<T> head;
	public Node2<T> tail;
	public Node2<T> current;
	
	private int head_status;
	private int tail_status;
	private int right_status;
	private int put_right_status;
	private int put_left_status;
	private int remove_status;
	private int add_to_empty_status;
	private int add_tail_status;
	private int replace_status;
	private int find_status;
	private int remove_all_status;
	private int get_status;
	private int get_left;
	
	private boolean is_empty() {
		return head == null;
	}
	@Override
	public void left() {
		if(current != head) {
			current = current.next;
			right_status = LEFT_OK;
		}
		if(current == head) {
			right_status = LEFT_ERR;
		}
	}

	@Override
	public int get_left_status() {
		return get_left;
	}

	@Override
	public void head() {
		if (!is_empty()) {
			current = head;
			head_status = HEAD_OK;
		}
		if(is_empty()) {
			head_status = HEAD_ERR;
		}
	}

	@Override
	public void tail() {
		if (!is_empty()) {
			current = tail;	
			tail_status = TAIL_OK;
		}
		
		if(is_empty()) {
			tail_status = TAIL_ERR;
		}
	}

	@Override
	public void right() {
		if(current != tail) {
			current = current.next;
			right_status = RIGHT_OK;
		}
		if(current == tail) {
			right_status = RIGHT_ERR;
		}
	}

	@Override
	public void put_right(T value) {
		if (!is_empty()) {
			Node2<T> exNext = current.next;
			current.next = new Node2<>(value);
			current.next.next = exNext;
			put_right_status = PUT_RIGHT_OK;
			
		}
		if(is_empty()) {
			put_right_status = PUT_RIGHT_ERR;
		}
	}

	@Override
	public void put_left(T value) {
		if (!is_empty()) {
			Node2<T> exPrev = current.prev;
			current.prev = new Node2<>(value);
			current.prev.prev = exPrev;
			put_left_status = PUT_LEFT_OK;
			
		}
		if(is_empty()) {
			put_left_status = PUT_LEFT_ERR;
		}
	}

	@Override
	public void remove() {
		if(is_empty()) {
			remove_status = REMOVE_ERR;
			return;
		}
		
		if(is_head()) {
			current.next.prev = null;
			current = current.next;
			head = current;
		}
		else if(is_tail()) {
			current.prev.next = null;
			current = current.prev;
			tail = current;
		}else {
			current.prev.next = current.next;
			current.next.prev = current.prev;
		}
		
		remove_status = REMOVE_OK;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		current = null;
	}

	@Override
	public void add_to_empty(T value) {
		if(!is_empty()) {
			add_to_empty_status = ADD_TO_EMP_ERR;
		}
		if(is_empty()) {
			head = new Node2<>(value);
			current = head;
			tail = head;
			add_to_empty_status = ADD_TO_EMP_OK;
		}
	}

	@Override
	public void add_tail(T value) {
		if(is_empty()) {
			add_tail_status = ADD_TAIL_ERR;
		}
		if(!is_empty()) {
			tail.next = new Node2<>(value);
			tail.next.prev = tail;
			tail = tail.next;		
			add_tail_status = ADD_TAIL_OK;
		}		
	}

	@Override
	public void replace(T value) {
		if(is_empty()) {
			replace_status = REPLACE_ERR;
		}
		if(!is_empty()) {
			current.value = value;
			replace_status = REPLACE_OK;
		}
	}

	@Override
	public void find(T value) {
		if(is_empty()) {
			find_status = FIND_ERR;
		}
		if(!is_empty()) {
			Node2<T> node = head;
			find_status = FIND_NF;
			while (node != null) {
				if (node.value == value) {
					current = node;
					find_status = FIND_OK;
					break;
				}				
				node = node.next;
			}
		}
	}

	@Override
	public void remove_all(T value) {
		if(is_empty()) {
			remove_all_status = REM_ALL_ERR;
		}
		if(!is_empty()) {
			Node2<T> node = head;
			
			while (node != null) {
				if (node.value.equals(value)) {
					if (node.prev == null) {
						head = node.next;
						if (head != null)
							head.prev = null;
					} else {
						node.prev.next = node.next;
					}
					if (node.next == null) {
						tail = node.prev;
						if (node.prev != null)
							node.prev.next = null;
					} else {
						node.next.prev = node.prev;
					}
				}
				node = node.next;
			}
			remove_all_status = REM_ALL_OK;
		}
	}

	@Override
	public T get() {
		if(!is_empty()) {
			get_status = GET_OK;
			return current.value;
		}
		get_status = GET_ERR;
		return null;
	}

	@Override
	public int size() {
		int count = 0;
		Node2<T> node = head;
		while (node != null) {
			node = node.next;
			count++;
		}

		return count;
	}

	@Override
	public boolean is_head() {
		return current == head;
	}

	@Override
	public boolean is_tail() {
		return current == tail;
	}

	@Override
	public boolean is_value() {
		return current == null;
	}

	@Override
	public int get_head_status() {
		return head_status;
	}

	@Override
	public int get_tail_status() {
		return tail_status;
	}

	@Override
	public int get_right_status() {
		return right_status;
	}

	@Override
	public int get_put_right_status() {
		return put_right_status;
	}

	@Override
	public int get_put_left_status() {
		return put_left_status;
	}

	@Override
	public int get_remove_status() {
		return remove_status;
	}

	@Override
	public int get_add_to_empty_status() {
		return add_to_empty_status;
	}

	@Override
	public int get_add_tail_status() {
		return add_tail_status;
	}

	@Override
	public int get_replace_status() {
		return replace_status;
	}

	@Override
	public int get_find_status() {
		return find_status;
	}

	@Override
	public int get_remove_all_status() {
		return remove_all_status;
	}

	@Override
	public int get_get_status() {
		return get_status;
	}

}
class Node2<T> {
	public T value;
	public Node2<T> next;
	public Node2<T> prev;

	public Node2(T _value) {
		value = _value;
		next = null;
		prev = null;
	}
}