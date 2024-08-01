package ooap1.queue;

public abstract class AbstractQueue<T> {
	
	//команды:
	//предусловие: нет
	//постусловие: элемент T item добавлен последним в очередь
	public abstract void enqueue(T item);
	
	//предусловие: очередь не пуста
	//постусловие: первый элемент удален из очереди
	public abstract T dequeue();	
	
	//запросы:
	//предусловие: нет
	public abstract int size();
	
	//предусловие: очередь не пуста
	public abstract T peek();
	
	//запросы статусов:

	public abstract int getDequeueStatus();//ок; очередь пуста
	
	public abstract int getPeekStatus();//ок; очередь пуста
	
}
