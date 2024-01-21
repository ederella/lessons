package trees;

import java.io.*;
import java.util.*;


class BSTNode<T> {
	public int NodeKey; // ключ узла
	public T NodeValue; // значение в узле
	public BSTNode<T> Parent; // родитель или null для корня
	public BSTNode<T> LeftChild; // левый потомок
	public BSTNode<T> RightChild; // правый потомок

	public BSTNode(int key, T val, BSTNode<T> parent) {
		NodeKey = key;
		NodeValue = val;
		Parent = parent;
		LeftChild = null;
		RightChild = null;
	}
}

// промежуточный результат поиска
class BSTFind<T> {
	
	public BSTNode<T> Node;// null если в дереве вообще нету узлов	
	public boolean NodeHasKey;// true если узел найден	
	public boolean ToLeft;// true, если родительскому узлу надо добавить новый левым

	public BSTFind() {
		Node = null;
	}
}

class BST<T>
{
    BSTNode<T> Root;
    
    public BST(BSTNode<T> node){
    	Root = node;
    }
    
    public BSTFind<T> FindNodeByKey(int key){
    	return FindNodeByKey(Root, key);     
    }
    
    private BSTFind<T> FindNodeByKey(BSTNode<T> node, int key) {
    	if(Root == null)
           return new BSTFind<T>();
    	
           if(key == node.NodeKey){
    		BSTFind<T> finded = new BSTFind<T>();
    		finded.Node = node;
    		finded.NodeHasKey = true;
    		finded.ToLeft = false;
    		return finded;
    	}

    	if(key < node.NodeKey){        	
    		if(node.LeftChild == null){
        		BSTFind<T> finded = new BSTFind<T>();
        		finded.Node = node;
        		finded.NodeHasKey = false;
        		finded.ToLeft = true;
        		return finded;
        	}   		
    		return FindNodeByKey(node.LeftChild, key);
    	}    	

    	if(node.RightChild == null){
    		BSTFind<T> finded = new BSTFind<T>();
    		finded.Node = node;
    		finded.NodeHasKey = false;
    		finded.ToLeft = false;
    		return finded;
    	}
    	return FindNodeByKey(node.RightChild, key);
	}

	public boolean AddKeyValue(int key, T val){
    	BSTFind<T> finded = FindNodeByKey(key);
    	
    	if(finded.NodeHasKey)
    		return false;
    	
    	BSTNode<T> newNode = new BSTNode<T>(key, val, finded.Node);
    	if(finded.Node == null){
    		Root = newNode;
    		return true;
    	}
    	if(finded.ToLeft){
    		finded.Node.LeftChild = newNode;
    		return true;
    	}
    	finded.Node.RightChild = newNode;
		return true;
    }
    
    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax){
      if(FromNode == null)
    	  return null;
     
      if(FindMax){
    	  return FindMax(FromNode);
      }
    	  return FindMin(FromNode);
    }
    
    private BSTNode<T> FindMax(BSTNode<T> fromNode) {
		if(fromNode.RightChild == null)
			return fromNode;
		
		return FindMax(fromNode.RightChild);
	}

	private BSTNode<T> FindMin(BSTNode<T> fromNode) {
		if(fromNode.LeftChild == null)
			return fromNode;
		
		return FindMin(fromNode.LeftChild);
	}

	public boolean DeleteNodeByKey(int key) {
		BSTFind<T> finded = FindNodeByKey(key);
		
		if(!finded.NodeHasKey)
			return false;
		
		if(isLeaf(finded.Node)) {
			return deleteLeaf(finded);
		}		
		if(finded.Node.LeftChild == null ) {
			return deleteNodeWithSingle(finded.Node.Parent, finded.Node.RightChild);
		}
		if(finded.Node.RightChild == null ) {
			return deleteNodeWithSingle(finded.Node.Parent, finded.Node.LeftChild);
		}
		return deleteNodeWithDouble(finded.Node);
    }

	private boolean deleteNodeWithDouble(BSTNode<T> node) {
		BSTNode<T> replacer = node.RightChild;
		
		while(replacer.LeftChild != null) {
			replacer = replacer.LeftChild;
		}
		
		//put right child of replacer to the place of replacer
		if (replacer.RightChild != null && replacer != node.RightChild) {
			replacer.RightChild.Parent = replacer.Parent;
			replacer.Parent.LeftChild = replacer.RightChild;
		}
		
		//put replacer to the place of deleting node
		//update parent
		replacer.Parent = node.Parent;
		if(replacer.Parent != null && replacer.Parent.NodeKey > replacer.NodeKey)
			replacer.Parent.LeftChild = replacer;
		
		if(replacer.Parent != null && replacer.Parent.NodeKey < replacer.NodeKey)
			replacer.Parent.RightChild = replacer;
		
		if(replacer.Parent == null)
			Root = replacer;		
		node.Parent = null;

		//update left child
		if(replacer != node.LeftChild)
			replacer.LeftChild = node.LeftChild;	
		if(replacer.LeftChild!= null)
			replacer.LeftChild.Parent = replacer;	
		node.LeftChild = null;
		
		//update right child		
		if(replacer != node.RightChild)
			replacer.RightChild = node.RightChild;
		if(replacer.RightChild!= null)
			replacer.RightChild.Parent = replacer;	
		node.RightChild = null;
		return true;		
	}

	private boolean deleteNodeWithSingle(BSTNode<T> parent, BSTNode<T> replacer) {
		if(parent == null) {
			Root = replacer;
			replacer.Parent = null;
			return true;
		}
		if(replacer.NodeKey < parent.NodeKey)
			parent.LeftChild = replacer;
		
		if(replacer.NodeKey > parent.NodeKey)
			parent.RightChild = replacer;
		
		replacer.Parent = parent;
		return true;
	}

	private boolean deleteLeaf(BSTFind<T> finded) {
		if(finded.Node == Root) {
			Root = null;
			return true;
		}
		if(finded.Node.NodeKey < finded.Node.Parent.NodeKey) 
			finded.Node.Parent.LeftChild = null;
		
		if(finded.Node.NodeKey > finded.Node.Parent.NodeKey) 
			finded.Node.Parent.RightChild = null;

		finded.Node.Parent = null;
		return true;
	}

	private boolean isLeaf(BSTNode<T> node) {
		return node == null || (node.LeftChild == null && node.RightChild == null);
	}

    public int Count(){
      return Count(Root);
    }

    private int Count(BSTNode<T> node){
    	if(node == null){
    		return 0;
    	}
    	return Count(node.LeftChild) + Count(node.RightChild) + 1;
    }
}