package ooap1.queue;

import java.util.ArrayList;

public class Queue<T> extends AbstractQueue<T>{
	
	public static int DEQUEUE_STATUS_OK = 1;
	public static int DEQUEUE_STATUS_ERR = 2;
	
	public static int PEEK_STATUS_OK = 1;
	public static int PEEK_STATUS_ERR = 2;

	private ArrayList<T> array;
	
	private int dequeueStatus;
	private int peekStatus;
	
	public Queue(){
		array = new ArrayList<>();
	}
	
	@Override
	public void enqueue(T item) {
		array.add(item);
	}

	@Override
	public T dequeue() {
		if(array.isEmpty()) {
			dequeueStatus = DEQUEUE_STATUS_ERR;
			return null;
		}
		
		T item = array.get(0);
		array.remove(0);
		dequeueStatus = DEQUEUE_STATUS_OK;
		return item;
	}

	@Override
	public int size() {
		return array.size();
	}

	@Override
	public T peek() {
		if(array.isEmpty()) {
			peekStatus = PEEK_STATUS_ERR;
			return null;
		}
		
		T item = array.get(0);
		peekStatus = PEEK_STATUS_OK;
		return item;
	}

	@Override
	public int getDequeueStatus() {
		return dequeueStatus;
	}

	@Override
	public int getPeekStatus() {
		return peekStatus;
	}

}
