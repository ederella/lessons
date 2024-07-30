package ooap1.list;

public abstract class TwoWayList<T> extends ParentList<T>{

	public static final int LEFT_OK = 1;
	public static final int LEFT_ERR = 2;
	
	//предусловие: список не пуст и курсор не указывает на первый узел
	//постусловие: курсор установлен на предыдущий узел в списке
	public abstract void left();
	
	public abstract int get_left_status();//returns LEFT_*
}
