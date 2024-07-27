package interval_tree;


import java.util.TreeSet;

public class Node {
	int median = -1;
	TreeSet<Interval> intervals;
	Node left;
	Node right;
	int minFrom = -1;
	int maxTo = -1;
	
	public Node() {
		intervals = new TreeSet<Interval>();
	}
}
