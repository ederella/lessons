package cartesian_tree;

import java.util.ArrayList;
import java.util.Collections;

public class CartesianTree {

	Node root;
	ArrayList<Pair> pairs;
	
	public CartesianTree(ArrayList<Pair> input){
		this.pairs = new ArrayList<Pair>();
		pairs.addAll(input);
		Collections.sort(pairs);

		root = create(0, pairs.size());
	}

	private Node create(int start, int finish) {
		if(start == finish - 1)
			return new Node(pairs.get(start));
		if(start >= finish)
			return null;
		
		int maxPriorityIdx = getIndexOfMaxPriority(start, finish);
		
		Node node = new Node(pairs.get(maxPriorityIdx));
		node.left = create(start, maxPriorityIdx);
		node.right = create(maxPriorityIdx + 1, finish);
		return node;
	}

	private int getIndexOfMaxPriority(int start, int finish) {
		int maxPriorityIdx = start;
		for (int  i = start + 1; i < finish; i++) {
			if(pairs.get(maxPriorityIdx).priority < pairs.get(i).priority){
				maxPriorityIdx = i;
			}
		}
		return maxPriorityIdx;
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

class Pair implements Comparable<Pair>{

	int key;
	int priority;
	
	public Pair (int key, int priority){
		this.key = key;
		this.priority = priority;
	}

	public int compareTo(Pair o) {
		return this.key > o.key ? 1 : this.key == o.key ? 0 : -1;
	}
	
	public String toString(){
		return "key = "+key + " priority = "+priority;	
	}
	
}
class Node {
	Pair pair;
	Node left;
	Node right;
	
	public Node(Pair pair){
		this.pair = pair;
	}

	public String toString(){
		return pair.toString();	
	}
}
