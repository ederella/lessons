package rmq_lca;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;

public class SectionTree {
	class Node {
		int value;

		int from;

		int to;

		Node parent;

		Node left;

		Node right;

		Node(int value, int from, int to) {
			this.value = value;
			this.from = from;
			this.to = to;
		}

		public String toString() {
			return "value = " + value + "\nfrom = " + from + "\nto = " + to + "\n";
		}
	}

	public Node node;
	
	private Function<int[], Integer>func;

	public SectionTree(int[] arrayN, int type, Function<int[], Integer> f) {
		func = f;
		if (type == 0)
			node = buildTreeDown(arrayN, 0, arrayN.length);
		node = buildTreeUp(arrayN, 0, arrayN.length);		
	}

	public Node buildTreeDown(int[] arrayN, int begin, int end) {
		if (begin >= end)
			return null;
		
		Node n = new Node(func.apply(Arrays.copyOfRange(arrayN, begin, end)), begin, end);

		if (begin == end - 1)
			return n;

		n.left = buildTreeDown(arrayN, begin, begin + (end - begin) / 2);
		if (n.left != null)
			n.left.parent = n;

		n.right = buildTreeDown(arrayN, begin + (end - begin) / 2, end);
		if (n.right != null)
			n.right.parent = n;

		return n;
	}

	public Node buildTreeUp(int[] arrayN, int begin, int end) {
		Queue<Node> leaves = new LinkedList<Node>();

		for (int i = 0; i < arrayN.length; i++) {
			leaves.add(new Node(arrayN[i], i, i + 1));
		}

		while (leaves.size() > 1) {
			Node left = leaves.poll();
			Node right = leaves.peek().from != left.to ? null : leaves.poll();

			if (right != null) {
				int[] ar = { left.value, right.value };
				Node n = new Node(func.apply(Arrays.copyOfRange(ar, 0, 2)), left.from, right.to);
				n.right = right;
				n.right.parent = n;
				n.left = left;
				n.left.parent = n;
				leaves.add(n);
			}
			if (right == null) {
				Node n = new Node(left.value, left.from, left.to);
				n.left = left;
				n.left.parent = n;
				leaves.add(n);
			}
		}
		return leaves.poll();
	}



	public int getFuncResult(int from, int to, Node n){
		if(n.from == from && n.to == to) {
			return n.value;
		}
		if(n.left.from <= from && n.left.to >=to) {
			return getFuncResult(from, to, n.left);
		}
		if(n.right.from <= from && n.right.to >=to) {
			return getFuncResult(from, to, n.right);
		}
		int res1 = getFuncResult(from, n.left.to, n.left);
		int res2 = getFuncResult(n.right.from, to, n.right);
		int[]arr = {res1, res2};
		return func.apply(arr);
	}

}
