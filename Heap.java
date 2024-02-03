package heap;

import java.util.*;

class Heap {
	public int[] HeapArray;

	public Heap() {
		HeapArray = null;
	}

	public void MakeHeap(int[] a, int depth) {
		HeapArray = new int[depth];
		if (a == null)
			return;
		int count = a.length > depth ? depth : a.length;

		for (int i = 0; i < depth; i++) {
			HeapArray[i] = -1;
		}
		for (int i = 0; i < count; i++) {
			Add(a[i]);
		}

	}

	public int GetMax() {
		int size = getSize();

		if (size == 0)
			return -1;

		int max = HeapArray[0];

		HeapArray[0] = HeapArray[size - 1];

		HeapArray[size - 1] = -1;

		siftDown(0);

		return max;
	}

	public boolean Add(int key) {
		int lastPos = getSize();
		if (lastPos >= HeapArray.length)
			return false;

		HeapArray[lastPos] = key;

		siftUp(lastPos);

		return true;
	}

	private int getSize() {
		for (int i = 0; i < HeapArray.length; i++) {
			if (HeapArray[i] < 0)
				return i;
		}
		return HeapArray.length;
	}

	private void siftUp(int pos) {
		if (pos <= 0)
			return;
		int parentPos = (pos - 1) / 2;

		if (HeapArray[parentPos] < HeapArray[pos]) {
			int parent = HeapArray[parentPos];
			HeapArray[parentPos] = HeapArray[pos];
			HeapArray[pos] = parent;
		}
		siftUp(parentPos);
	}

	private void siftDown(int pos) {

		int leftChildPos = 2 * pos + 1;
		int rightChildPos = 2 * pos + 2;
		int targetPos = leftChildPos;
		if (targetPos >= HeapArray.length)
			return;
		if (HeapArray[rightChildPos] > HeapArray[leftChildPos])
			targetPos = rightChildPos;

		if (HeapArray[targetPos] > HeapArray[pos]) {
			int parent = HeapArray[targetPos];
			HeapArray[targetPos] = HeapArray[pos];
			HeapArray[pos] = parent;
		}
		siftDown(targetPos);
	}
}
