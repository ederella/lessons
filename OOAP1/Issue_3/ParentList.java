package ooap1.list;

public abstract class ParentList<T> {


	public static final int HEAD_OK = 1;
	public static final int HEAD_ERR = 2;

	public static final int TAIL_OK = 1;
	public static final int TAIL_ERR = 2;
	
	public static final int RIGHT_OK = 1;
	public static final int RIGHT_ERR = 2;
	
	public static final int PUT_RIGHT_OK = 1;
	public static final int PUT_RIGHT_ERR = 2;
	
	public static final int PUT_LEFT_OK = 1;
	public static final int PUT_LEFT_ERR = 2;
	
	public static final int REMOVE_OK = 1;
	public static final int REMOVE_ERR = 2;
	
	public static final int ADD_TO_EMP_OK = 1;
	public static final int ADD_TO_EMP_ERR = 2;
	
	public static final int ADD_TAIL_OK = 1;
	public static final int ADD_TAIL_ERR = 2;
	
	public static final int REPLACE_OK = 1;
	public static final int REPLACE_ERR = 2;
	
	public static final int FIND_OK = 1;
	public static final int FIND_ERR = 2;
	public static final int FIND_NF = 3;
	
	public static final int REM_ALL_OK = 1;
	public static final int REM_ALL_ERR = 2;
	
	public static final int GET_OK = 1;
	public static final int GET_ERR = 2;

	// команды:
	
	//предусловие: список не пуст
	//постусловие: курсор установлен на 1й узел в списке
	public abstract void head();
	
	//предусловие: список не пуст
	//постусловие: курсор установлен на последний узел в списке
	public abstract void tail();
	
	//предусловие: список не пуст и курсор не указывает на последний узел
	//постусловие: курсор установлен на следующий узел в списке
	public abstract void right();
	
	//предусловие: список не пуст
	//постусловие: добавлен новый элемент справа от курсора
	public abstract void put_right(T value);
	
	//предусловие: список не пуст
	//постусловие: добавлен новый элемент слева от курсора
	public abstract void put_left(T value);
	
	//предусловие: список не пуст
	//постусловие: текущий узел удален, курсор указывает на последующий (если есть) или предшествующий (если есть) узел
	public abstract void remove();
	
	//постусловие: из списка удалены все значения, курсор = null
	public abstract void clear();

	//предусловие: список пуст
	//постусловие: в список добавлен новый элемент, курсор указывает на новый элемент
	public abstract void add_to_empty(T value);

	//предусловие: список не пуст
	//постусловие: в список добавлен последний элемент
	public abstract void add_tail(T value);

	//предусловие: список не пуст
	//постусловие: значение текущего узла заменено
	public abstract void replace(T value);
	
	//предусловие: список не пуст и курсор не установлен на последний элемент в списке
	//постусловие: курсор установлен на следующий узел с искомым значением
	public abstract void find(T value);
	
	//предусловие: список не пуст
	//постусловие: удалены все элементы с искомым значением
	public abstract void remove_all(T value);

	
	// запросы:	
	
	//предусловие: список не пуст
	public abstract T get();
	
	public abstract int size();
	public abstract boolean is_head();
	public abstract boolean is_tail();
	public abstract boolean is_value();
	
	
	//запросы статусов
	public abstract int get_head_status();//returns HEAD_*
	
	public abstract int get_tail_status();//returns TAIL_*

	public abstract int get_right_status();//returns RIGHT_*
	
	public abstract int get_put_right_status();//returns RIGHT_*
	
	public abstract int get_put_left_status();//returns LEFT_*
	
	public abstract int get_remove_status();//returns REMOVE_*
	
	public abstract int get_add_to_empty_status();//returns ADD_TO_EMP_*
	
	public abstract int get_add_tail_status();//returns ADD_TAIL_*
	
	public abstract int get_replace_status();//returns REPLACE_*
	
	public abstract int get_find_status();//returns FIND_*
	
	public abstract int get_remove_all_status();//returns REM_ALL_*
	
	public abstract int get_get_status();//returns GET_*
}