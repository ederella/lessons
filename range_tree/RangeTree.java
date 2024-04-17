package range_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;

public class RangeTree {
	Node root;
	
	public RangeTree(List<Point> pointsIn) {
		Collections.sort(pointsIn, new Comparator<Point>(){
			@Override
			public int compare(Point o1, Point o2) {
				return o1.x > o2.x ? 1 : o1.x < o2.x ? -1 : 0;
			}			
		});
		root = buildTree(pointsIn);
	}

	private Node buildTree(List<Point> pointsIn) {
		Queue<Node> leaves = new LinkedList<Node>();
		
		for (Point point : pointsIn) {
			Node n = new Node(point);
			n.yTree.add(point);
			leaves.add(n);
		}
		while (leaves.size() > 1) {
			Node left = leaves.poll();
			Node right = leaves.peek().level != left.level ? null : leaves.poll();
			
			if (right != null) {				
				Node n = new Node(left.getMax().point);
				n.right = right;
				n.left = left;
				n.yTree.addAll(right.yTree);
				n.yTree.addAll(left.yTree);
				n.level =  left.level + 1;
				leaves.add(n);
			}
			if (right == null) {
				Node n = new Node(left.point);
				n.left = left;
				n.level = left.level + 1;
				leaves.add(n);
			}
		}
		return leaves.poll();
	}

	
	public List<Point> search(Point p1, Point p2, Node node){
		List<Point> result = new ArrayList<Point>();
		if(node.isLeaf() && node.point.isBetween(p1, p2)) {
			result.add(node.point);
			return result;
		}
		if(node.point.isBetweenX(p1, p2)) {
			return searchY(p1, p2, node.yTree);
		}
		
		if(node.point.x > p2.x) {
			 result.addAll(search(p1, p2, node.left));
		}
		if(node.point.x < p1.x) {
			result.addAll(search(p1, p2, node.right));
		}
		return result;
	}
	
	private List<Point> searchY(Point p1, Point p2, TreeSet<Point> y) {
		List<Point> result = new ArrayList<Point>();
		for (Point point : y) {
			if(point.isBetween(p1, p2))
				result.add(point);
		}
		return result;
	}


	public String toString(){
		return recursivelyPrint(root, "");
	}
	
	private String recursivelyPrint(Node node,String delimiter) {
		if(node == null)
			return "-";
		String res = node.toString();
		res+="\n" + delimiter + "left " + recursivelyPrint(node.left, delimiter+"  ");
		res+="\n" + delimiter + "right " + recursivelyPrint(node.right, delimiter+"  ");
		return res;
	}
}
