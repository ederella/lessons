package ooap1.set;

import ooap1.hashTable.AbstractHashTable;

public abstract class AbstractPowerSet<T> extends AbstractHashTable<T> {

	// запросы
	// возвращает пересечение текущего множества
	// с множеством set
	public abstract AbstractPowerSet<T> intersection(AbstractPowerSet<T> set);

	// возвращает объединение текущего множества
	// и множества set
	public abstract AbstractPowerSet<T> union(AbstractPowerSet<T> set);

	// возвращает разницу между текущим множеством
	// и множеством set
	public abstract AbstractPowerSet<T> difference(AbstractPowerSet<T> set);

	// проверка, будет ли set подмножеством
	// текущего множества
	public abstract boolean isSubset(AbstractPowerSet<T> set);


}
