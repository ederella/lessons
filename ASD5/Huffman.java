package huffman;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Map.Entry;

class SimpleTreeNode {
	public Character value;

	public int priority;

	public SimpleTreeNode parent;

	public SimpleTreeNode left;

	public SimpleTreeNode right;

	public SimpleTreeNode(char value, int priority) {
		this.value = value;
		this.priority = priority;
		this.left = null;
		this.right = null;
	}
}

public class Huffman {

	public static HashMap<Character, String> buildDictionary(String string) {
		PriorityQueue<SimpleTreeNode> lettersFreq = parseWithFrequencies(string);

		modifyAsTree(lettersFreq);
		
		HashMap<Character, String> dict = new HashMap<Character, String>();
			
		encodeLetters(lettersFreq.peek(), dict, "");
	
		return dict;
	}

	private static void encodeLetters(SimpleTreeNode parentNode, HashMap<Character, String> dict, String code) {
		if(parentNode == null)
			return;
		if (parentNode.value != '\u0000')
			dict.put(parentNode.value, code);

		encodeLetters(parentNode.left, dict, code + "0");
		encodeLetters(parentNode.right, dict, code + "1");
	}

	private static void modifyAsTree(PriorityQueue<SimpleTreeNode> lettersFreq) {
		while (lettersFreq.size() > 1) {
			SimpleTreeNode node1 = lettersFreq.poll();
			SimpleTreeNode node2 = lettersFreq.poll();
	
			SimpleTreeNode parent = new SimpleTreeNode('\u0000', node1.priority + node2.priority);
			parent.left = node1;
			node1.parent = parent;
			
			parent.right = node2;
			node2.parent = parent;
			
			lettersFreq.add(parent);	
		}
	}

	private static PriorityQueue<SimpleTreeNode> parseWithFrequencies(String string) {

		HashMap<Character, Integer> charsAndFreq = new HashMap<Character, Integer>();

		for (int i = 0; i < string.length(); i++) {
			int count = charsAndFreq.containsKey(string.charAt(i))? charsAndFreq.get(string.charAt(i)) + 1 : 1;
			charsAndFreq.put(string.charAt(i), count);
		}

		PriorityQueue<SimpleTreeNode> lettersFreq = new PriorityQueue<SimpleTreeNode>(charsAndFreq.size(), new Comparator<SimpleTreeNode>() {
			public int compare(SimpleTreeNode r1, SimpleTreeNode r2) {
				return r1.priority - r2.priority;
			}
		});
		
		for (Entry<Character, Integer> entry : charsAndFreq.entrySet()) {
			lettersFreq.add(new SimpleTreeNode(entry.getKey(), entry.getValue()));
		}
		
		return lettersFreq;
	}
}
