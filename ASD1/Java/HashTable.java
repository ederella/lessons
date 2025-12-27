package hashtable;

public class HashTable{

	public int size;
	public int step;
	public String[] slots;

	public HashTable(int sz, int stp) {
		size = sz;
		step = stp;
		slots = new String[size];

		for (int i = 0; i < size; i++)
			slots[i] = null;
	}

	public int hashFun(String value) {
		return value.length() % size;
	}

	public int seekSlot(String value) {
		int slotIdx = hashFun(value);

		for(int i = slotIdx;;){
			if (slots[i] == null)
				return i;
			i = (i + step) % size;
			
			if(i == slotIdx)
				break;
		}
		return -1;
	}

	public int put(String value){

		int freeSlotIdx = seekSlot(value);
		if (freeSlotIdx != -1)
			slots[freeSlotIdx] = value;

		return freeSlotIdx;
	}

	public int find(String value) {
		int slotIdx = hashFun(value);

		for(int i = slotIdx;;){
			if (slots[i] == value)
				return i;
			
			i = (i + step) % size;
			
			if(i == slotIdx)
				break;
		}		
		return -1;
	}

}
