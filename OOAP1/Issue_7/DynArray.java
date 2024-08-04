package ooap1.dynArray;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DynArray<T> extends AbstractDynArray<T>{

	private static final int MINIMAL_CAPACITY = 16;
	private static final int INCREASE_CAPACITY_COEF = 2;
	private static final float DEREASE_CAPACITY_COEF = 1.5f;
	private static final int DECREASE_CAPACITY_VALUE = 2;

	public T[] array;

	public int count;

	public int capacity;

	Class clazz;
	
	private int makeArrayStatus;
	private int getItemStatus;
	private int appendStatus;
	private int insertStatus;
	private int removeStatus;

	public DynArray(Class clz) {
		clazz = clz;
		count = 0;
		array = (T[]) Array.newInstance(this.clazz, MINIMAL_CAPACITY);
		capacity = MINIMAL_CAPACITY;
	}

	public void makeArray(int newCapacity) {
		if (newCapacity == capacity) {
			makeArrayStatus = MAKE_ARR_NA;
			return;
		}
		array = Arrays.copyOf(array, newCapacity);
		capacity = newCapacity;
		makeArrayStatus = MAKE_ARR_OK;
	}

	public T getItem(int index) {
		if(isCorrectIndex(index, 0, count)) {
			getItemStatus = GET_OK;
			return array[index];
		}
		getItemStatus = GET_ERR;
		return null;
	}

	public void append(T itm) {
		increaseArray();
		if(count > capacity) {
			appendStatus = APPEND_ERR;
			return;
		}	
		
		array[count] = itm;
		count++;
		appendStatus = APPEND_OK;
	}

	public void insert(T itm, int index) {
		if(isCorrectIndex(index, 0, count + 1)) {
			increaseArray();
			for (int i = count; i > index; i--) {
				array[i] = array[i - 1];
			}
			array[index] = itm;
			count++;
			insertStatus = INSERT_OK;
		}else {
			insertStatus = INSERT_ERR;
		}
	}

	public void remove(int index) {
		if(isCorrectIndex(index, 0, count)) {
			for (int i = index; i < count - 1; i++) {
				array[i] = array[i + 1];
			}
			array[count - 1] = null;
			count--;
			decreaseArray();
			removeStatus = REMOVE_OK;
		}else {
			removeStatus = REMOVE_ERR;
		}
	}

	private boolean isCorrectIndex(int index, int start, int finish) {
		return (index < start || index >= finish);			
	}

	private void increaseArray() {
		if (count < capacity)
			return;

		makeArray(capacity * INCREASE_CAPACITY_COEF);

	}

	private void decreaseArray() {
		if (DECREASE_CAPACITY_VALUE * count >= capacity)
			return;

		int newCapacity = (int) (capacity / DEREASE_CAPACITY_COEF);

		if (newCapacity < MINIMAL_CAPACITY)
			newCapacity = MINIMAL_CAPACITY;

		array = Arrays.copyOf(array, newCapacity);
	}

	@Override
	public int getMakeArrStatus() {
		return makeArrayStatus;
	}

	@Override
	public int getAppendStatus() {
		return appendStatus;
	}

	@Override
	public int getInsertStatus() {
		return insertStatus;
	}

	@Override
	public int getRemoveStatus() {
		return removeStatus;
	}

	@Override
	public int getGetItemStatus() {
		return getItemStatus;
	}
}
