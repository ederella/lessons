package disjoint_set_union;

public class Node<T> {
	private T value;
	
	private Node<T> parent;	
	
	public Node(T value){
		this.value = value;
		parent = this;
	}
	
	public Node(T value, Node<T> parent){
		this.value = value;
		this.parent = parent;
	}
	
	public T getValue(){
		return this.value;
	}
	public void setValue(T value){
		this.value = value;
	}
	public Node<T> getParent(){
		return this.parent;
	}
	public void setParent(Node<T> parent){
		this.parent = parent;
	
