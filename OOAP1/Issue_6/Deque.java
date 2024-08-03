package ooap1.queue;

import java.util.ArrayList;

public class Deque<T> extends AbstractDeque<T>{

	public static int REM_FIRST_STATUS_OK = 1;
	public static int REM_FIRST_STATUS_ERR = 2;
	
	public static int GET_FIRST_STATUS_OK = 1;
	public static int GET_FIRST_STATUS_ERR = 2;

	public static int REM_LAST_STATUS_OK = 1;
	public static int REM_LAST_STATUS_ERR = 2;
	
	public static int GET_LAST_STATUS_OK = 1;
	public static int GET_LAST_STATUS_ERR = 2;
	
	private ArrayList<T> array;
	
	private int removeFirstStatus;
	private int getFirstStatus;
	private int removeLastStatus;
	private int getLastStatus;
	
	public Deque(){
		array = new ArrayList<>();
	}
	@Override
	public void addFirst(T item) {
		array.add(0, item);
	}

	@Override
	public T removeLast() {
		if(array.isEmpty()) {
			removeLastStatus = REM_LAST_STATUS_ERR;
			return null;
		}
		
		T item = array.get(array.size());
		array.remove(array.size());
		removeLastStatus = REM_LAST_STATUS_OK;
		return item;
	}

	@Override
	public T getLast() {
		if(array.isEmpty()) {
			getLastStatus = GET_LAST_STATUS_ERR;
			return null;
		}
		
		T item = array.get(array.size());
		getLastStatus = GET_LAST_STATUS_OK;
		return item;
	}

	@Override
	public int getRemoveLastStatus() {
		return removeLastStatus;
	}

	@Override
	public int getGetLastStatus() {
		return getLastStatus;
	}

	@Override
	public void addLast(T item) {
		array.add(item);
	}

	@Override
	public T removeFirst() {
		if(array.isEmpty()) {
			removeFirstStatus = REM_FIRST_STATUS_ERR;
			return null;
		}
		
		T item = array.get(0);
		array.remove(0);
		removeFirstStatus = REM_FIRST_STATUS_OK;
		return item;
	}

	@Override
	public int size() {
		return array.size();
	}

	@Override
	public T getFirst() {
		if(array.isEmpty()) {
			getFirstStatus = GET_FIRST_STATUS_ERR;
			return null;
		}
		
		T item = array.get(0);
		getFirstStatus = GET_FIRST_STATUS_OK;
		return item;
	}

	@Override
	public int getRemoveFirstStatus() {
		return removeFirstStatus;
	}

	@Override
	public int getGetFirstStatus() {
		return getFirstStatus;
	}

}
