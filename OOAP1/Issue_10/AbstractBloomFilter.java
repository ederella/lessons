package ooap1.bloom;

public abstract class AbstractBloomFilter {

	//команды
	//предусловие: нет
	//постусловие: элемент добавлен
	public abstract void add(String str1);
	
	
	//запросы
	public abstract int hash1(String str1);
	
	public abstract int hash2(String str1);
	
	public abstract boolean isValue(String str1);
}
