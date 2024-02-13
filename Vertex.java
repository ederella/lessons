package graph;

import java.util.*;

class Vertex {
	public int Value;

	public boolean Hit;

	public Vertex(int val) {
		Value = val;
		Hit = false;
	}
}

class SimpleGraph {
	Vertex[] vertex;

	int[][] m_adjacency;

	int max_vertex;

	public SimpleGraph(int size) {
		max_vertex = size;
		m_adjacency = new int[size][size];
		vertex = new Vertex[size];
	}

	public void AddVertex(int value) {
		int vertextCount = getCount();
		if (vertextCount < vertex.length) {
			vertex[vertextCount] = new Vertex(value);
		}
	}

	private int getCount() {
		for (int i = 0; i < vertex.length; i++) {
			if (vertex[i] == null)
				return i;
		}
		return vertex.length;
	}

	public void RemoveVertex(int v) {
		for (int i = 0; i < m_adjacency.length; i++) {
			RemoveEdge(v, i);
		}
		vertex[v] = null;
	}

	public boolean IsEdge(int v1, int v2) {
		return m_adjacency[v1][v2] == 1 || m_adjacency[v2][v1] == 1;
	}

	public void AddEdge(int v1, int v2) {
		if (vertex[v1] == null)
			return;
		if (vertex[v2] == null)
			return;
		m_adjacency[v1][v2] = 1;
		m_adjacency[v2][v1] = 1;
	}

	public void RemoveEdge(int v1, int v2) {
		m_adjacency[v1][v2] = 0;
		m_adjacency[v2][v1] = 0;
	}

	public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {

		Stack<Integer> dfsStack = new Stack<Integer>();
		clearHits();

		DepthFirstSearch(VFrom, VTo, dfsStack);

		ArrayList<Vertex> path = new ArrayList<Vertex>();
		for (int i = 0; i < dfsStack.size(); i++) {
			path.add(vertex[dfsStack.get(i)]);
		}

		return path;
	}

	private void DepthFirstSearch(int VFrom, int VTo, Stack<Integer> dfsStack) {

		vertex[VFrom].Hit = true;
		dfsStack.push(VFrom);

		if (IsEdge(VFrom, VTo)) {
			dfsStack.push(VTo);
			return;
		}
		for (int i = 0; i < vertex.length; i++) {
			if (IsEdge(VFrom, i) && !vertex[i].Hit) {
				DepthFirstSearch(i, VTo, dfsStack);
				return;
			}
		}

		dfsStack.pop();

		if (dfsStack.isEmpty())
			return;
		DepthFirstSearch(dfsStack.pop(), VTo, dfsStack);
	}

	public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
		Queue<Integer> bfsQueue = new LinkedList<Integer>();
		ArrayList<Vertex> path = new ArrayList<Vertex>();
		clearHits();

		bfsQueue.add(VFrom);
		vertex[VFrom].Hit = true;
		path.add(vertex[VFrom]);

		while (!bfsQueue.isEmpty()) {
			int current = bfsQueue.poll();
			boolean existsNew = false;

			if (IsEdge(current, VTo)) {
				vertex[VTo].Hit = true;
				path.add(vertex[VTo]);
				removeExcessFromPath(path, bfsQueue);
				return path;
			}

			for (int i = 0; i < vertex.length; i++) {
				if (IsEdge(current, i) && !vertex[i].Hit) {
					vertex[i].Hit = true;
					bfsQueue.add(i);
					existsNew = true;
					path.add(vertex[i]);
				}
			}
			if (!existsNew) {
				path.remove(vertex[current]);
			}
		}

		return new ArrayList<Vertex>();
	}

	private void removeExcessFromPath(ArrayList<Vertex> path, Queue<Integer> bfsQueue) {
		while (!bfsQueue.isEmpty()) {
			path.remove(vertex[bfsQueue.poll()]);
		}
	}

	private void clearHits() {
		for (int i = 0; i < vertex.length; i++) {
			vertex[i].Hit = false;
		}
	}

}