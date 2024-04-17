package binomial_heap;

public class BinomialHeap<T extends Comparable<T>> {
	BinomialTree<T> rootTree;

	int size;

	public void insert(T value) {
		size++;

		BinomialTree<T> newTree = new BinomialTree<T>(value);
		if (rootTree != null) {
			newTree.next = rootTree;
		}
		rootTree = newTree;

		if (size % 2 == 0) {
			rootTree = mergeAll(rootTree);
		}
	}

	private BinomialTree<T> mergeAll(BinomialTree<T> tree) {
		if (tree.next == null)
			return tree;
		if (tree.size == tree.next.size) {
			BinomialTree<T> newTree = merge(tree, tree.next);
			newTree.next = tree.next.next;
			return mergeAll(newTree);
		}
		return tree;
	}

	public BinomialTree<T> merge(BinomialTree<T> tree1, BinomialTree<T> tree2) {
		if (tree1 == null)
			return tree2;
		if (tree2 == null)
			return tree1;

		BinomialTree<T> minorTree = tree1;
		BinomialTree<T> majorTree = tree2;
		if (tree1.root.value.compareTo(tree2.root.value) > 0) {
			majorTree = tree1;
			minorTree = tree2;
		}

		BinomialTree<T> newTree = new BinomialTree<T>(majorTree.root.value);
		minorTree.root.right = majorTree.root.left;
		newTree.root.right = null;
		newTree.root.left = minorTree.root;
		newTree.size = minorTree.size + majorTree.size;
		return newTree;
	}

	public String toString() {
		BinomialTree<T> tree = rootTree;
		String res = "";
		while (tree != null) {
			res += tree.toString() + "\n";
			tree = tree.next;
		}
		return res;
	}

}

class BinomialTree<T extends Comparable<T>> {
	Node<T> root;

	BinomialTree<T> next;

	int size;

	public BinomialTree(T value) {
		root = new Node<T>(value);
		size = 1;
	}

	public String toString() {
		return recursivelyPrint(root, "");
	}

	private String recursivelyPrint(Node<T> node, String delimiter) {
		if (node == null)
			return "-";
		String res = node.toString();
		res += "\n" + delimiter + "left " + recursivelyPrint(node.left, delimiter + "  ");
		res += "\n" + delimiter + "right " + recursivelyPrint(node.right, delimiter + "  ");
		return res;
	}

}

class Node<T extends Comparable<T>> {
	T value;

	Node<T> left;

	Node<T> right;

	public Node(T value) {
		this.value = value;
	}

	public String toString() {
		return value.toString();
	}
}
