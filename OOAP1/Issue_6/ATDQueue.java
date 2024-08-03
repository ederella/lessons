package ooap1.queue;

public abstract class ATDQueue<T> {

	//команды:
	//предусловие: нет
	//постусловие: элемент T item добавлен последним в очередь
	public abstract void addLast(T item);
	
	//предусловие: очередь не пуста
	//постусловие: первый элемент удален из очереди
	public abstract T removeFirst();	
	
	//запросы:
	//предусловие: нет
	public abstract int size();
	
	//предусловие: очередь не пуста
	public abstract T getFirst();
	
	//запросы статусов:

	public abstract int getRemoveFirstStatus();//ок; очередь пуста
	
	public abstract int getGetFirstStatus();//ок; очередь пуста
}
