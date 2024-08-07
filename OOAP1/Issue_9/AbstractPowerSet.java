package ooap1.set;

import ooap1.hashTable.AbstractHashTable;

public abstract class AbstractPowerSet<T> extends AbstractHashTable<T>{
	
	//команды:
	//предусловие: максимум не достигнут и элемента нет во множестве
	//постусловие: элемент есть во множестве
	public abstract void put(T value);
	
	//предусловие: элемент есть во множестве
	//постусловие: элемента нет во множестве
	public abstract void remove(T value);
	
	//запросы:
	public abstract boolean existsValue(T value);
	
	public abstract boolean isMaxReached();

	//запросы статусов
	public abstract int getPutStatus();//ok, элемент есть во множестве
	public abstract int getRemoveStatus();//ok, элемента нет во множестве
}
