package additionalASD;

import java.util.Iterator;
import java.util.Stack;

public class ImmStack<T> implements Iterable<T> {
	private Stack<T> innerStack;
	
	public ImmStack() {
		innerStack = new Stack<T>();
	}

	public ImmStack<T> push(T value){
		ImmStack<T> is = new ImmStack<T>();
		innerStack.forEach(e->is.innerStack.add(e));
		is.innerStack.push(value);
		return is;
	}
	
	public ImmStack<T> pop(){
		ImmStack<T> is = new ImmStack<T>();
		innerStack.forEach(e->is.innerStack.add(e));
		is.innerStack.pop();
		return is;
	}
	
	@Override
	public Iterator<T> iterator() {
		return innerStack.iterator();
	}
	

}
