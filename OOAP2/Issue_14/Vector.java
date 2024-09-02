package ooap2_14;


public class Vector<T extends General> implements Summable<Vector<T>> {

	General array[];
	int current = 0;
	
	public Vector(int size){
		array = new General[size];
	}
	public int getSize(){
		return array.length;
	}
	public General getValueAt(int i){
		return array[i];
	}
	public void setValueAt(int i, General value){
		array[i] = value;
	}
	public void add(General g) {
		array[current++] = g;
	}
	@Override
	public Vector<T> sum(Vector<T> value) {
		if(value.getSize() != this.array.length)
			return null;
		
		Vector<T> res = new Vector<>(getSize());
		for(int i = 0; i < array.length; i++){
			res.setValueAt(i, array[i].sum(value.getValueAt(i)));
		}
		return res;
	}
	
	

}
