package cash;

import java.lang.reflect.Array;

class NativeCache<T>
{
    public int size;
    public String [] slots;
    public T [] values;
    public int [] hits;

    
    public NativeCache(int sz, Class clazz) {
		size = sz;
		slots = new String[size];
		values = (T[]) Array.newInstance(clazz, this.size);
		hits = new int[size];
	}
    
	public int hashFun(String key) {
		return key.length() % size;
	}

	public boolean isKey(String key) {
		for (int i = 0; i < size; i++) {
			if (slots[i] != null && slots[i].equals(key))
				return true;
		}
		return false;
	}

    public void put(String key, T value) {
		int slotIdx = hashFun(key);
		for (int i = slotIdx;;) {
			if (slots[i] == null || slots[i].equals(key)) {
				slots[i] = key;
				values[i] = value;
				return;
			}
			i = (i + 1) % size;
			if (i == slotIdx) {
				i = getIndexOfElementWithMinHits();
				slots[i] = key;
				values[i] = value;
				hits[i] = 0;
				return;
			}
		}
	}

    private int getIndexOfElementWithMinHits() {
    	int indexOfMin = 0;
    	
    	for (int i = 1; i < size; i++) {
			if (hits[i] < hits[indexOfMin]){
				indexOfMin = i;
			}
			if(hits[indexOfMin] == 0)
				break;
		}    	
		return indexOfMin;
	}

	public T get(String key) {
		for (int i = 0; i < size; i++) {
			if (slots[i] != null && slots[i].equals(key)){
				hits[i]++;
				return values[i];
			}
		}
		return null;
	}
}

