package ooap1.queue;

public abstract class AbstractDeque<T> extends ATDQueue<T>{
	//команды:
	//предусловие: нет
	//постусловие: элемент T item добавлен первым
	public abstract void addFirst(T item);

	//предусловие: deque не пуст
	//постусловие: последний элемент удален
	public abstract T removeLast();	
	
	//предусловие: очередь не пуста
	public abstract T getLast();
	
	//запросы статусов:

	public abstract int getRemoveLastStatus();//ок; deque пуст

	public abstract int getGetLastStatus();//ок; deque пуст

}
