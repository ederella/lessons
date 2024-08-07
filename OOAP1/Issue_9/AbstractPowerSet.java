package ooap1.set;

public abstract class AbstractPowerSet<T> {
	
	//команды:
	//предусловие: элемента нет во множестве
	//постусловие: элемент есть во множестве
	public abstract void put(T value);
	
	//предусловие: элемент есть во множестве
	//постусловие: элемента нет во множестве
	public abstract void remove(T value);
	
	//запросы:
	public abstract boolean existsValue(T value);
	
	public abstract int size();

	public abstract T[] toArray();
	
	public abstract boolean isSubset(AbstractPowerSet<T> set2);
	
	//запросы статусов
	public abstract int getPutStatus();//ok, элемент есть во множестве
	public abstract int getRemoveStatus();//ok, элемента нет во множестве
}
