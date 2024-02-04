package graph;

import java.util.*;

class Vertex {
	public int Value;

	public Vertex(int val) {
		Value = val;
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
}