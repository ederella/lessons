package dictionary;

import java.lang.reflect.Array;

class NativeDictionary<T>
{
    public int size;
    public String [] slots;
    public T [] values;

    public NativeDictionary(int sz, Class clazz) {
		size = sz;
		slots = new String[size];
		values = (T[]) Array.newInstance(clazz, this.size);
	}

	public int hashFun(String key) {
		return key.length() % size;
	}

	public boolean isKey(String key) {
		for (int i = 0; i < size; i++) {
			if (slots[i].equals(key))
				return true;
		}
		return false;
	}

    public void put(String key, T value)
    {
     	int slotIdx = hashFun(key);    	
    	for(int i = slotIdx;;){
    		if(slots[i] == null || slots[i].equals(key)){
    			slots[i] = key;
    			values[i] = value;
    			return;
    		}
    		i = (i+1)%size;
    		if(i == slotIdx){
    			break;
    		}
    	}
    }

    public T get(String key) {
		for (int i = 0; i < size; i++) {
			if (slots[i] != null && slots[i].equals(key))
				return values[i];
		}
		return null;
	}
}
