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
	
	private ArrayList<T> set;
	private int putStatus;
	private int removeStatus;
	private Class clz;
	
	public PowerSet(Class clz) {
		set = new ArrayList<>();
		this.clz = clz;
	}
	@Override
	public void put(T value) {
		if(!existsValue(value)) {
			putStatus = PUT_STATUS_OK;
			set.add(value);
			return;
		}
		putStatus = PUT_STATUS_ERR;
	}

	@Override
	public void remove(T value) {
		if(existsValue(value)) {
			removeStatus = REMOVE_STATUS_OK;
			set.remove(value);
			return;
		}
		removeStatus = REMOVE_STATUS_ERR;
	}

	@Override
	public boolean existsValue(T value) {
		for(int i = 0; i < set.size(); i++) {
			if(set.get(i).equals(value))
				return true;
		}
		return false;
	}

	@Override
	public int size() {
		return set.size();
	}

	@Override
	public boolean isSubset(AbstractPowerSet<T> set2) {
		T[] set2Values = set2.toArray();
		for(int i = 0; i < set2Values.length; i++) {
			if(!existsValue(set2Values[i]))
				return false;
		}
		return true;
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
	public T[] toArray() {
		T[] arr = (T[]) Array.newInstance(this.clz, size());
		
		return set.toArray(arr);
	}

}
