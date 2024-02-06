package trees;

import java.util.*;

public class SimpleTreeNode<T> {

	public T NodeValue;

	public SimpleTreeNode<T> Parent;

	public List<SimpleTreeNode<T>> Children;

	public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {

		NodeValue = val;
		Parent = parent;
		Children = null;
	}
}

class SimpleTree<T> {

	public SimpleTreeNode<T> Root;

	public SimpleTree(SimpleTreeNode<T> root) {
		Root = root;
	}

	public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {

		if (NewChild == null)
			return;

		if (ParentNode == null) {
			Root = NewChild;
			return;

		}

		if (ParentNode.Children == null)
			ParentNode.Children = new ArrayList<SimpleTreeNode<T>>();

		ParentNode.Children.add(NewChild);
		NewChild.Parent = ParentNode;

	}

	public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
		if (NodeToDelete == null || NodeToDelete == Root) {
			return;
		}

		if (GetAllNodes().contains(NodeToDelete)) {
			if (NodeToDelete.Parent != null && NodeToDelete.Parent.Children != null)
				NodeToDelete.Parent.Children.remove(NodeToDelete);

			NodeToDelete.Parent = null;
		}
	}

	public List<SimpleTreeNode<T>> GetAllNodes() {

		if (Root == null)
			return new ArrayList<SimpleTreeNode<T>>();

		return GetAllNodes(Root);
	}

	private List<SimpleTreeNode<T>> GetAllNodes(SimpleTreeNode<T> node) {

		List<SimpleTreeNode<T>> outList = new ArrayList<SimpleTreeNode<T>>();
		outList.add(node);

		if (node.Children == null || node.Children.isEmpty()) {
			return outList;
		}

		for (SimpleTreeNode<T> child : node.Children) {
			outList.addAll(GetAllNodes(child));
		}

		return outList;
	}

	public List<SimpleTreeNode<T>> FindNodesByValue(T val) {

		if (Root == null)
			return new ArrayList<SimpleTreeNode<T>>();

		return FindNodesByValue(Root, val);

	}

	private List<SimpleTreeNode<T>> FindNodesByValue(SimpleTreeNode<T> node, T val) {

		List<SimpleTreeNode<T>> outList = new ArrayList<SimpleTreeNode<T>>();
		if (node.NodeValue.equals(val))
			outList.add(node);

		if (node.Children == null || node.Children.isEmpty()) {
			return outList;
		}

		for (SimpleTreeNode<T> child : node.Children) {
			outList.addAll(FindNodesByValue(child, val));
		}
		return outList;
	}

	public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {

		List<SimpleTreeNode<T>> list = GetAllNodes();

		if (list.contains(OriginalNode) && list.contains(NewParent)) {
			DeleteNode(OriginalNode);
			AddChild(NewParent, OriginalNode);
		}
	}

	public int Count() {
		return Count(Root);

	}

	private int Count(SimpleTreeNode<T> node) {
		if (node == null)
			return 0;

		int count = 1;

		if (node.Children != null) {
			for (SimpleTreeNode<T> child : node.Children) {
				count += Count(child);
			}
		}
		return count;
	}

	public int LeafCount() {
		return LeafCount(Root);

	}

	private int LeafCount(SimpleTreeNode<T> node) {
		if (node == null)
			return 0;

		if (node.Children == null || node.Children.isEmpty())
			return 1;

		int count = 0;
		for (SimpleTreeNode<T> child : node.Children) {
			count += LeafCount(child);
		}
		return count;
	}
	
	public ArrayList<T> EvenTrees(){
		return EvenTrees(Root);
	}
	
	public ArrayList<T> EvenTrees(SimpleTreeNode<T> node){
		ArrayList<T> outList = new ArrayList<T>();
		
		int count = Count(node);
		if(count == 1)
			return outList;
		
		if (count % 2 == 0) {
			if (node.Parent != null) {
				outList.add(node.Parent.NodeValue);
				outList.add(node.NodeValue);
			}
		}
			
		for (SimpleTreeNode<T> child : node.Children) {
			outList.addAll(EvenTrees(child));
		}

		return outList;		
	}
}