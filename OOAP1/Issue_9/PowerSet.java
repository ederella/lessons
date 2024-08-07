package ooap1.set;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class PowerSet<T> extends AbstractPowerSet<T>{
	public static final int REMOVE_STATUS_OK = 1;
	public static final int REMOVE_STATUS_ERR = 2;
	
	public static final int PUT_STATUS_OK = 1;
	public static final int PUT_STATUS_ERR = 2;
	
	private int size;
	private int count;
	private int step;
	public T[] slots;
	Class clazz;
	private int putStatus;
	private int removeStatus;
	

	public PowerSet(int sz, int stp, Class clz) {
		size = sz;
		step = stp;
		clazz = clz;
		slots = (T[]) Array.newInstance(this.clazz, size);

		for (int i = 0; i < size; i++)
			slots[i] = null;
	}
	
	@Override
	public void put(T t) {
		putStatus = PUT_STATUS_ERR;
		if (find(t) != -1)
			return;
		
		int freeSlotIdx = findSlot(t);
		if (freeSlotIdx != -1) {
			slots[freeSlotIdx] = t;
			putStatus = PUT_STATUS_OK;
			count++;
		}

	}

	
	private int findSlot(T t) {
		int slotIdx = hashFun(t);

		for(int i = slotIdx;;){
			if (slots[i] == null) {
				return i;
			}
			i = (i + step) % size;
			
			if(i == slotIdx)
				break;
		}
		return -1;
	}

	@Override
	public int find(T t) {
		int slotIdx = hashFun(t);

		for(int i = slotIdx;;){
			if (slots[i] == t)
				return i;
			
			i = (i + step) % size;
			
			if(i == slotIdx)
				break;
		}		
		return -1;
	}

	@Override
	public int hashFun(T t) {
		return t.toString().length() % size;
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
	public void remove(T t) {
		int slot = find(t);
		if(slot!=-1) {
			slots[slot] = null;
			removeStatus = REMOVE_STATUS_OK;
			count--;
		}
		if(slot==-1)
			removeStatus = PUT_STATUS_ERR;
		
	}

	@Override
	public int size() {
		return count;
	}


	@Override
	public AbstractPowerSet<T> intersection(AbstractPowerSet<T> set) {
		ArrayList<T> al= new ArrayList<>();
		for(int i = 0; i < slots.length ; i++) {
			if(set.find(slots[i]) != -1) {
				al.add(slots[i]);
			}				
		}
		return new PowerSet<T>(al.size(),this.step, this.clazz);
	}

	@Override
	public AbstractPowerSet<T> union(AbstractPowerSet<T> set) {
		ArrayList<T> al= new ArrayList<>();
		for(int i = 0; i < slots.length ; i++) {
				if(!al.contains(slots[i]))
					al.add(slots[i]);
			}				
		return new PowerSet<T>(al.size(),this.step, this.clazz);
	}

	@Override
	public AbstractPowerSet<T> difference(AbstractPowerSet<T> set) {
		ArrayList<T> al= new ArrayList<>();
		for(int i = 0; i < slots.length ; i++) {
				if(set.find(slots[i]) == -1) {
					al.add(slots[i]);
				}	
			}				
		return new PowerSet<T>(al.size(),this.step, this.clazz);
	}

	@Override
	public boolean isSubset(AbstractPowerSet<T> set) {
		for(int i = 0; i < slots.length ; i++) {
			if(set.find(slots[i]) != -1)
				return true;			
		}
		return false;
	}



}
