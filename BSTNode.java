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
    	
    	if(Root == null){
            BSTFind<T> finded = new BSTFind<T>();
            finded.Node = null;
            finded.NodeHasKey = false;
            finded.ToLeft = false;
            return finded;
    	}
    	
    	return FindNodeByKey(Root, key);
     
    }
    
    private BSTFind<T> FindNodeByKey(BSTNode<T> node, int key) {
    	
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
		
		if(finded.Node == Root && Count() == 1) {
			Root = null;
			return true;
		}			
		
		if(finded.Node.LeftChild == null ) {
			if(finded.Node == Root) {
				Root = finded.Node.RightChild;
				Root.Parent = null;
				finded.Node.RightChild = null;
				return true;
			}
			if(isRight(finded.Node)) {
				finded.Node.Parent.RightChild = finded.Node.RightChild;
			}
			if(!isRight(finded.Node)){
				finded.Node.Parent.LeftChild = finded.Node.RightChild;
			}
			if(finded.Node.RightChild !=null)
				finded.Node.RightChild.Parent = finded.Node.Parent;
			
			finded.Node.Parent = null;
			finded.Node.RightChild = null;
			return true;
		}
		
		if(finded.Node.RightChild == null ) {
			if(finded.Node == Root) {
				Root = finded.Node.LeftChild;
				Root.Parent = null;
				finded.Node.LeftChild = null;
				return true;
			}
			if(isRight(finded.Node)) {
				finded.Node.Parent.RightChild = finded.Node.LeftChild;
			}
			if(!isRight(finded.Node)){
				finded.Node.Parent.LeftChild = finded.Node.LeftChild;
			}
			if(finded.Node.LeftChild !=null)
				finded.Node.LeftChild.Parent = finded.Node.Parent;
			
			finded.Node.Parent = null;
			finded.Node.LeftChild = null;
			return true;
		}
     
		BSTNode<T> replacer = finded.Node.RightChild;
		
		while(replacer.LeftChild != null) {
			replacer = replacer.LeftChild;
		}
		
		if (replacer.RightChild != null && replacer != finded.Node.RightChild) {
			replacer.RightChild.Parent = replacer.Parent;
			replacer.Parent.LeftChild = replacer.RightChild;
		}
		
		replacer.Parent = finded.Node.Parent;
		replacer.LeftChild = finded.Node.LeftChild;	
		if(replacer != finded.Node.RightChild)
			replacer.RightChild = finded.Node.RightChild;	
		
		if(replacer.Parent != null && replacer.Parent.NodeKey > replacer.NodeKey)
			replacer.Parent.LeftChild = replacer;
		
		if(replacer.Parent != null && replacer.Parent.NodeKey <= replacer.NodeKey)
			replacer.Parent.RightChild = replacer;
		
		if(replacer.Parent == null)
			Root = replacer;
		
		if(finded.Node.LeftChild!= null)
			finded.Node.LeftChild.Parent = replacer;
		
		finded.Node.Parent = null;
		finded.Node.LeftChild = null;
		finded.Node.RightChild = null;
		return true;		
    }

	private boolean isRight(BSTNode<T> node) {
		return node.Parent.NodeKey <= node.NodeKey;
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