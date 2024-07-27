package skew_heap;


public class SkewHeap {
	Node root;
	
	public SkewHeap(Node root){
		this.root = root;
	}
	
	public static SkewHeap merge(SkewHeap h1, SkewHeap h2){
		return new SkewHeap(merge(h1.root, h2.root));
	}
	
	private static Node merge(Node rootH1, Node rootH2){
		if(rootH2 == null)
			return rootH1;
		if(rootH1 == null)
			return rootH2;
		
		Node minorNode = rootH1;
		Node majorNode = rootH2;
		if(minorNode.key > majorNode.key){
			minorNode = rootH2;
			majorNode = rootH1;
		}

		Node leftSubtreeOfMinor = minorNode.right;		
		minorNode.right = minorNode.left;
		minorNode.left = merge(majorNode, leftSubtreeOfMinor);
		
		return minorNode;
	}
	
	public void insert(Node node){
		root = merge(root, node);
	}
	
	public void deleteRoot(){
		root = merge(root.left, root.right);
	}
	
	public String toString(){
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

class Node{
	int key;
	Node left;
	Node right;
	
	public Node(int key){
		this.key = key;
	}
	
	public String toString(){
		return String.valueOf(key);
	}
}
