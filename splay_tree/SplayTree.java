package splay_tree;

public class SplayTree<T extends Comparable<T>> {

	Node<T> root;

	public SplayTree() {
	}

	public SplayTree(Node<T> root) {
		this.root = root;
	}

	public SplayTree(T rootVal) {
		this.root = new Node<T>(rootVal);
	}

	public void add(Node<T> node) {
		add(root, node);
	}

	private void add(Node<T> parent, Node<T> node) {
		boolean isToRight = node.value.compareTo(parent.value) > 0;
		if (isToRight && parent.right == null) {
			addRight(parent, node);
			return;
		}
		if (isToRight && parent.right != null) {
			add(parent.right, node);
			return;
		}
		boolean isToLeft = node.value.compareTo(parent.value) < 0;
		if (isToLeft && parent.left == null) {
			addLeft(parent, node);
			return;
		}
		if (isToLeft && parent.left != null) {
			add(parent.left, node);
			return;
		}
	}

	private void addLeft(Node<T> parent, Node<T> left) {
		parent.left = left;
		left.parent = parent;
	}

	private void addRight(Node<T> parent, Node<T> right) {
		parent.right = right;
		right.parent = parent;
	}

	public void insert(Node<T> node) {
		add(node);
		splay(node);
	}

	public void remove(Node<T> node) {
		splay(node);
		SplayTree<T> leftTree = new SplayTree<T>(node.left);
		SplayTree<T> rightTree = new SplayTree<T>(node.right);
		node = null;
		leftTree.merge(rightTree);
		root = leftTree.root;
	}

	public boolean merge(SplayTree<T> otherTree) {
		if (!isRightTree(otherTree.getMin(), root))
			return false;
		Node<T> maxLeft = getMax();
		splay(maxLeft);

		maxLeft.right = otherTree.root;
		otherTree.root.parent = maxLeft;
		return true;
	}

	private boolean isRightTree(Node<T> minOtherTreeNode, Node<T> node) {
		if (node == null) {
			return true;
		}
		if (minOtherTreeNode.value.compareTo(node.value) <= 0) {
			return false;
		}
		if (minOtherTreeNode.value.compareTo(node.value) > 0) {
			return isRightTree(minOtherTreeNode, node.left) && isRightTree(minOtherTreeNode, node.right);
		}
		return false;
	}

	public Node<T> getMax() {
		Node<T> max = root;
		while (max.right != null) {
			max = max.right;
		}
		return max;
	}

	public Node<T> getMin() {
		Node<T> min = root;
		while (min.left != null) {
			min = min.left;
		}
		return min;
	}

	public Node<T> find(T value) {
		Node<T> result = find(root, value);
		if (result != null)
			splay(result);
		return result;
	}

	private Node<T> find(Node<T> parent, T value) {
		if (parent == null)
			return null;

		if (parent.value.compareTo(value) > 0)
			return find(parent.left, value);

		if (parent.value.compareTo(value) < 0)
			return find(parent.right, value);

		return parent;
	}

	public void splay(Node<T> x) {
		if (x == root) {
			return;
		}
		if (root.left == x) {
			moveRightUp(x);
			return;
		}
		if (root.right == x) {
			moveLeftUp(x);
			return;
		}
		if (root.left.left == x) {
			moveRightUp(x.parent);
			moveRightUp(x);
			return;
		}
		if (root.right.right == x) {
			moveLeftUp(x.parent);
			moveLeftUp(x);
			return;
		}
		if (root.left.right == x) {
			moveLeftUp(x);
			moveRightUp(x);
			return;
		}
		if (root.right.left == x) {
			moveRightUp(x);
			moveLeftUp(x);
			return;
		}

		splay(x.parent);
	}

	private void moveLeftUp(Node<T> x) {
		Node<T> xLeftChild = x.left;
		Node<T> xParent = x.parent;

		x.parent = xParent.parent;

		x.left = xParent;
		xParent.parent = x;

		xParent.right = xLeftChild;
		if (xLeftChild != null)
			xLeftChild.parent = xParent;

		if (x.parent == null) {
			root = x;
			return;
		}
		if (x.left == x.parent.right)
			x.parent.right = x;
		if (x.left == x.parent.left)
			x.parent.left = x;
	}

	private void moveRightUp(Node<T> x) {
		Node<T> xRightChild = x.right;
		Node<T> xParent = x.parent;

		x.parent = xParent.parent;

		x.right = xParent;
		xParent.parent = x;

		xParent.left = xRightChild;
		if (xRightChild != null)
			xRightChild.parent = xParent;

		if (x.parent == null) {
			root = x;
			return;
		}
		if (x.left == x.parent.right)
			x.parent.right = x;
		if (x.left == x.parent.left)
			x.parent.left = x;
	}

	public String toString() {
		return recursivelyPrint(root, "");
	}

	private String recursivelyPrint(Node node, String delimiter) {
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

	Node<T> parent, left, right;

	public Node() {
	}

	public Node(T value) {
		this.value = value;
	}

	public String toString() {
		return value.toString();
	}
}
