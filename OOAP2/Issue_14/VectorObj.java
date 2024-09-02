package ooap2_14;

import java.lang.reflect.Type;
import java.util.Arrays;

public class VectorObj<E> implements Summable<VectorObj<E>> {

	Object[] array;
	int current = 0;

	public VectorObj(int size) {
		array = new Object[size];
	}

	public int getSize() {
		return array.length;
	}

	public Object getValueAt(int i) {
		return array[i];
	}

	public void setValueAt(int i, E value) {
		array[i] = value;
	}

	public void add(E g) {
		array[current++] = g;
	}

	@Override
	public VectorObj<E> sum(VectorObj<E> value) {
		if (array.length != value.getSize())
			return null;
		
		Type[] t =  value.getClass().getInterfaces();
		if(!Arrays.asList(t).get(0).equals(Summable.class))
			return null;

		VectorObj<E> res = new VectorObj<E>(array.length);
		for (int i = 0; i < array.length; i++) {
			res.setValueAt(i, ((Summable<E>) this.getValueAt(i)).sum((E) value.getValueAt(i)));
		}

		return res;
	}

}
