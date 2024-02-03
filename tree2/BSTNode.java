package trees;

import java.util.*;

class BSTNode {
	public int NodeKey;
	public BSTNode Parent;
	public BSTNode LeftChild;
	public BSTNode RightChild;
	public int Level;

	public BSTNode(int key, BSTNode parent) {
		NodeKey = key;
		Parent = parent;
		LeftChild = null;
		RightChild = null;
	}
}

class BalancedBST {
	public BSTNode Root;

	public BalancedBST() {
		Root = null;
	}

	public void GenerateTree(int[] a) {
		int[] sortedA = Arrays.copyOf(a, a.length);
		Arrays.sort(sortedA);
		Root = generate(null, 0, sortedA.length, sortedA, 0);

	}

	public BSTNode generate(BSTNode parent, int start, int finish, int[] sortedA, int level) {
		if (start >= finish)
			return null;

		int keyIndex = (finish - start) / 2 + start;
		BSTNode node = new BSTNode(sortedA[keyIndex], parent);
		node.Level = level;
		node.LeftChild = generate(node, start, keyIndex, sortedA, level + 1);
		node.RightChild = generate(node, keyIndex + 1, finish, sortedA, level + 1);
		return node;
	}

	public boolean IsBalanced(BSTNode root_node) {
		if (root_node == null)
			return true;

		int depthLeft = root_node.LeftChild == null? root_node.Level : getMaxLevel(root_node.LeftChild);
		int depthRigth = root_node.RightChild == null? root_node.Level :getMaxLevel(root_node.RightChild);
		
		if (Math.abs(depthLeft - depthRigth) <= 1 
				&& IsBalanced(root_node.LeftChild)
				&& IsBalanced(root_node.RightChild))
			return true;
		return false;
	}

	private int getMaxLevel(BSTNode node) {
		if (node == null)
			return -1;

		if (node.LeftChild == null && node.RightChild == null)
			return node.Level;

		return Math.max(getMaxLevel(node.LeftChild), getMaxLevel(node.RightChild));
	}
}