package ooap1.dict;

import java.lang.reflect.Array;

public class NativeDictionary<K, V> extends AbstractNativeDictionary<K, V>{

	private int size;
    private int actualCount;
    private K [] slots;
    private V [] values;
    
    private int putStatus;
    private int removeStatus;  
    private int getStatus;
    private int existsStatus;
    
	public static final int PUT_OK = 1;
	public static final int PUT_BAD_KEY = 2;
	public static final int PUT_ERR = 3;
	
	public static final int REMOVE_OK = 1;
	public static final int REMOVE_BAD_KEY = 2;
	public static final int REMOVE_NF = 3;
	
	public static final int GET_OK = 1;
	public static final int GET_BAD_KEY = 2;
	public static final int GET_NF = 3;
	
	public static final int EXISTS_OK = 1;
	public static final int EXISTS_BAD_KEY = 2;
    
    public NativeDictionary(int sz, Class clazzK, Class clazzV) {
		size = sz;
		slots = (K[]) Array.newInstance(clazzK, this.size);
		values = (V[]) Array.newInstance(clazzV, this.size);
	}
    
	@Override
	public void put(K key, V value) {
		if(key == null || key.toString().length() == 0) {
			putStatus = PUT_BAD_KEY;
			return;
		}
		
		int slotIdx = hashFun(key);
		for (int i = slotIdx;;) {
			if (slots[i] == null || slots[i].equals(key)) {
				slots[i] = key;
				values[i] = value;
				putStatus = PUT_OK;
				actualCount++;
				return;
			}
			i = (i + 1) % size;
			if (i == slotIdx) {
				putStatus = PUT_ERR;
				break;
			}
		}
	}
	private int hashFun(K key) {
		return key.toString().length() % size;
	}

	@Override
	public void remove(K key) {
		if(key == null || key.toString().length() == 0) {
			removeStatus = REMOVE_BAD_KEY;
			return;
		}
		
		for (int i = 0; i < size; i++) {
			if (slots[i] != null && slots[i].equals(key)) {
				removeStatus = REMOVE_OK;
				slots[i] = null;
				values[i] = null;
				actualCount--;
				return;
			}
		}
		removeStatus = REMOVE_NF;
	}

	@Override
	public V get(K key) {
		if(key == null || key.toString().length() == 0) {
			getStatus = GET_BAD_KEY;
			return null;
		}
		for (int i = 0; i < size; i++) {
			if (slots[i] != null && slots[i].equals(key)) {
				getStatus = GET_OK;
				return values[i];
			}
		}
		getStatus = GET_NF;
		return null;
	}

	@Override
	public boolean existKey(K key) {
		if(key == null || key.toString().length() == 0) {
			existsStatus = EXISTS_BAD_KEY;
			return false;
		}

		for (int i = 0; i < size; i++) {
			if (key.equals(slots[i])) {
				existsStatus = EXISTS_OK;
				return true;
			}
		}
		existsStatus = EXISTS_OK;
		return false;
	}

	@Override
	public int size() {
		return actualCount;
	}

	@Override
	public int getPutStatus() {
		return putStatus;
	}

	@Override
	public int getRemoveStatus() {
		return removeStatus;
	}

	@Override
	public int getGetStatus() {
		return getStatus;
	}

	@Override
	public int getExistsStatus() {
		return existsStatus;
	}

}
