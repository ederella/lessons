package ooap1;

import java.util.ArrayList;
import java.util.List;

public class BoundedStack<T> {
	// скрытые поля
	private List<T> stack; // основное хранилище стека
	private int peekStatus; // статус запроса peek()
	private int popStatus; // статус команды pop()
	private int pushStatus; // статус команды push()
	private final int maxSize;

	// интерфейс класса, реализующий АТД Stack
	public static final int POP_NIL = 0;
	public static final int POP_OK = 1;
	public static final int POP_ERR = 2;

	public static final int PEEK_NIL = 0;
	public static final int PEEK_OK = 1;
	public static final int PEEK_ERR = 2;

	public static final int PUSH_NIL = 0;
	public static final int PUSH_OK = 1;
	public static final int PUSH_ERR = 2;

	public static final int DEFAULT_SIZE = 32;

	
	public BoundedStack() {
		this(DEFAULT_SIZE);
	}

	public BoundedStack(int maxSize) {
		this.stack = new ArrayList<>(maxSize);
		this.maxSize = maxSize;
		this.peekStatus = PEEK_NIL;
		this.popStatus = POP_NIL;
		this.pushStatus = PUSH_NIL;
	}

	public void push(T value) {
		if (size() < maxSize) {
			stack.add(value);
			pushStatus = PUSH_OK;
		} else {
			pushStatus = PUSH_ERR;
		}
	}

	public void pop() {
		int size = size();
		if (size > 0) {
			stack.remove(size - 1);
			popStatus = POP_OK;
		} else
			popStatus = POP_ERR;
	}

	public void clear() {
		stack.clear();
		peekStatus = PEEK_NIL;
		popStatus = POP_NIL;
	}

	public T peek() {
		int size = size();
		if (size > 0) {
			peekStatus = PEEK_OK;
			return stack.get(size - 1);
		}
		popStatus = PEEK_ERR;
		return null;
	}

	public int size() {
		return stack.size();
	}

	// запросы статусов
	public int getPopStatus() {
		return popStatus;
	}

	public int getPeekStatus() {
		return peekStatus;
	}

	public int getPushStatus() {
		return pushStatus;
	}
}
