package range_tree;

import java.util.Comparator;
import java.util.TreeSet;

public class Node {
	Point point;
	
	Node left;
	Node right;

	int level;
	TreeSet<Point> yTree;

	public Node(Point point) {
		this.point = point;
		yTree = new TreeSet<Point>(new Comparator<Point>(){
			@Override
			public int compare(Point o1, Point o2) {
				return o1.y > o2.y ? -1 : o1.y < o2.y ? 1 : 0;
			}			
		});
	}

	public String toString() {
		return "point: " + point.toString() + " {" + yTreePrint() + "}";
	}
	
	public String yTreePrint(){
		String res = "";
		
		for (Point point : yTree) {
			res+=point.toString();
		}
		
		return res;
	}
	

	public Node getMax() {
		return getMax(this);
	}
	
	private Node getMax(Node n) {
		if(n.right == null)
			return n;
		return getMax(n.right);
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}
}
