package ooap1.queue;

import java.util.ArrayList;

public class Queue<T> extends AbstractQueue<T>{
	
	public static int REM_FIRST_STATUS_OK = 1;
	public static int REM_FIRST_STATUS_ERR = 2;
	
	public static int GET_FIRST_STATUS_OK = 1;
	public static int GET_FIRST_STATUS_ERR = 2;

	private ArrayList<T> array;
	
	private int removeFirstStatus;
	private int getFirstStatus;
	
	public Queue(){
		array = new ArrayList<>();
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
