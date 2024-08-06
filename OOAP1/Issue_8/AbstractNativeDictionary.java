package ooap1.dict;

public abstract class AbstractNativeDictionary<K, V> {

	//Команды:
	//предусловие: key - не null
	//постусловие: элемент добавлен
	public abstract void put(K key, V value);
	
	//предусловие: key - не null, элемент с ключом key найден
	//постусловие: элемент с ключом key удален
	public abstract void remove(K key);
	
	//Запросы:	
	//предусловие: key - не null
	//послусловие: элемент найден
	public abstract V get(K key);
	
	//предусловие: key - не null
	public abstract boolean existKey(K key);
		
	public abstract int size();
	
	//Статусы:
	public abstract int getPutStatus();//ok, bad key, no slot
	public abstract int getRemoveStatus();//ok, bad key, not found
	public abstract int getGetStatus();//ok, bad key, not found
	public abstract int getExistsStatus();//ok, bad key
}
