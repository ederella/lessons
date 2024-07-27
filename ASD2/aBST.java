package trees;

class aBST {
	public Integer Tree[];

	public aBST(int depth) {

		int tree_size = (2<<depth) - 1;
		
		Tree = new Integer[tree_size];
		
		for (int i = 0; i < tree_size; i++)
			Tree[i] = null;
	}

	public Integer FindKeyIndex(int key) {
		return FindKeyIndex(0, key);
	}

	private Integer FindKeyIndex(int treeIdx, int key) {
		if (treeIdx >= Tree.length) {
			return null;
		}
		if(Tree[treeIdx] == null)
			return -treeIdx;
		
		if (Tree[treeIdx] > key)
			return FindKeyIndex(2 * treeIdx + 1, key);

		if (Tree[treeIdx] < key)
			return FindKeyIndex(2 * treeIdx + 2, key);

		return treeIdx;
	}
	
	public int AddKey(int key) {
		Integer finded = FindKeyIndex(key);
		if (finded == null)
			return -1;

		if (finded < 0) {
			int treeIdx = -finded;
			Tree[treeIdx] = key;
			return treeIdx;
		}
		if (Tree[0] == null){
			Tree[0] = key;
			return 0;
		}

		if( finded >= 0) {
			return finded;
		}
		
		return -1;
	}
}
