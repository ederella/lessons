package dynarray;

import java.lang.reflect.Array;

public class DynArray<T> {
	
	private final static int MINIMAL_CAPACITY = 16;	
	private final static int INCREASE_CAPACITY_COEF = 2;	
	private final static float DEREASE_CAPACITY_COEF = 1.5f;
	private final static int DECREASE_CAPACITY_VALUE = 2;
	
	public T[] array;

	public int count;

	public int capacity;

	Class clazz;

	public DynArray(Class clz) {
		clazz = clz; 
		count = 0;
		makeArray(MINIMAL_CAPACITY);
	}

	public void makeArray(int new_capacity) {
		array = (T[]) Array.newInstance(this.clazz, new_capacity);
		capacity = new_capacity;
	}

	public T getItem(int index) {
		checkIndex(index, 0, count);
		return array[index];
	}

	public void append(T itm) {
		increaseArray();			
		array[count] = itm;
		count++;
	}


	public void insert(T itm, int index) {
		checkIndex(index, 0, count + 1);
		increaseArray();
		for (int i = count; i > index; i--) {
			array[i] = array[i - 1];
		}
		array[index] = itm;
		count++;
	}

	public void remove(int index) {
		checkIndex(index, 0, count);
		for (int i = index; i < count - 1; i++) {
			array[i] = array[i + 1];
		}
		array[count - 1] = null;
		decreaseArray();
		count--;
	}
	
	private void checkIndex(int index, int start, int finish) {
		if (index < start || index >= finish)
			throw new IndexOutOfBoundsException("Incorrect index");
	}
	
	private void increaseArray() {
		if(count < capacity)
			return;
		
		capacity *= INCREASE_CAPACITY_COEF;	
		T[] newArray = (T[]) Array.newInstance(this.clazz, capacity);		
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}

	private void decreaseArray() {		
		if (DECREASE_CAPACITY_VALUE * count >= capacity)
			return;
		
		int newCapacity = (int)(capacity /DEREASE_CAPACITY_COEF);	
				
		if (newCapacity < MINIMAL_CAPACITY)
			return;
		
		capacity = newCapacity;		
		T[] newArray = (T[]) Array.newInstance(this.clazz, capacity);
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;	
	}
}

