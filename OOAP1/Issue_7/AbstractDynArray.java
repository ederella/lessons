package ooap1.dynArray;

public abstract class AbstractDynArray<T> {
	
	public static final int MAKE_ARR_OK = 1;
	public static final int MAKE_ARR_NA = 2;
	
	public static final int APPEND_OK = 1;
	public static final int APPEND_ERR = 2;
	
	public static final int INSERT_OK = 1;
	public static final int INSERT_ERR = 2;
	
	public static final int REMOVE_OK = 1;
	public static final int REMOVE_ERR = 2;
	
	public static final int GET_OK = 1;
	public static final int GET_ERR = 2;

	
	//команды
	//предусловие: размер внутреннего массива не равен newCapacity
	//постусловие: размер внутреннего массива установлен равным newCapacity
	public abstract void makeArray(int newCapacity);
	
	//постусловие: элемент добавлен в конец массива
	public abstract void append(T item);
	
	//предусловие: индекс >0 и <= количеству элементов массива
	//постусловие: новый элемент добавлен в i-ю позицию, все элементы справа сдвинуты на 1 позицию
	public abstract void insert(T item, int i);
	
	//предусловие: индекс >0 и < количеству элементов массива
	//постусловие: элемент в позиции i удален, на его место установлены элементы справа от удаленного
	public abstract void remove(int i);
	
	//запросы
	//предусловие: индекс находится в пределах границ внутреннего массива
	public abstract T getItem(int i);
	
	//статусы
	public abstract int getMakeArrStatus();// возвращает статус из MAKE_ARR*
	public abstract int getAppendStatus();// возвращает статус из APPEND*
	public abstract int getInsertStatus();// возвращает статус из INSERT*
	public abstract int getRemoveStatus();// возвращает статус из REMOVE*
	public abstract int getGetItemStatus();// возвращает статус из GET*
}
