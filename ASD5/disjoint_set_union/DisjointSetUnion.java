package disjoint_set_union;

import java.util.HashSet;

public class DisjointSetUnion<T> {

	HashSet<Node<T>> nodes;
	
	public DisjointSetUnion() {
		nodes = new HashSet<Node<T>>();
	}

	public void makeSet(Node<T> node) {
		nodes.add(node);
	}

	public void unite(Node<T> x, Node<T> y) {
		if (find(x) != find(y)) {
			Node<T> newParent = Math.random() > 0.5 ? x : y;
			x.setParent(newParent);
			y.setParent(newParent);
		}
	}

	public Node<T> find(Node<T> node) {
		if(node.getParent() == node)
			return node;
		
		Node<T> root = find(node.getParent());
		node.setParent(root);
		
		return root;
	}
}
