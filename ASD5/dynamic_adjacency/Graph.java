package dynamic_adjacency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
	List<List<Integer>> adjacency;

	List<Integer> connectedComponents;

	List<Integer> connCompWithNoBridges;

	List<int[]> bridges;

	HashMap<Integer, HashMap<Integer, List<Integer>>> forest;

	public Graph(int size) {
		adjacency = new ArrayList<List<Integer>>();
		connectedComponents = new ArrayList<Integer>();
		connCompWithNoBridges = new ArrayList<Integer>();
		bridges = new ArrayList<int[]>();
		forest = new HashMap<Integer, HashMap<Integer, List<Integer>>>();

		for (int i = 0; i < size; i++) {
			adjacency.add(new ArrayList<Integer>());
			connectedComponents.add(i);
			connCompWithNoBridges.add(i);
			HashMap<Integer, List<Integer>> node = new HashMap<Integer, List<Integer>>();
			ArrayList<Integer> vert = new ArrayList<Integer>();
			vert.add(i);
			node.put(i,  vert);
			forest.put(i, node);
		}
	}

	public void addEdge(int v1, int v2) {
		if (v1 == v2)
			return;
		if (adjacency.get(v1).contains(v2) || adjacency.get(v2).contains(v1))
			return;
		adjacency.get(v1).add(v2);
		adjacency.get(v2).add(v1);
		
		if (v1 > v2) {
			int v = v1;
			v1 = v2;
			v2 = v;
		}
		
		if(connectedComponents.get(v1) != connectedComponents.get(v2)){
			forest.get(connectedComponents.get(v1)).putAll(forest.get(connectedComponents.get(v2)));
			forest.remove(connectedComponents.get(v2));
			
			int[] bridge = { v1, v2 };
			bridges.add(bridge);
			
			connectedComponents.set(getRootParent(v2), getRootParent(v1));
			for (Integer vertex : adjacency.get(v2)) {
				connectedComponents.set(getRootParent(vertex), getRootParent(v2));
			}
			return;
		}
		if(connCompWithNoBridges.get(v1) != connCompWithNoBridges.get(v2)){
			findBridges();
			makeForest();
		}
	}

	public void deleteEdge(int v1, int v2) {
		if (adjacency.get(v1).contains(v2))
			adjacency.get(v1).remove((Integer) v2);
		if (adjacency.get(v2).contains(v1))
			adjacency.get(v2).remove((Integer) v1);
	}

	public boolean isConnected(int v1, int v2) {
		boolean[] visited = new boolean[adjacency.size()];

		return isConnected(v1, v2, visited);

	}

	private boolean isConnected(final int vertex1, final int vertex2, final boolean[] visited) {
		visited[vertex1] = true;

		if (adjacency.get(vertex1).contains(vertex2))
			return true;

		for (Integer connectedV : adjacency.get(vertex1)) {
			if (!visited[connectedV]) {
				return isConnected(connectedV, vertex2, visited);
			}
		}
		return false;
	}

	public boolean isConnectedDSU(int v1, int v2) {
		return connectedComponents.get(v1) == connectedComponents.get(v2);
	}

	public void findConnectedComponents() {
		for (int i = 0; i < adjacency.size(); i++) {
			for (Integer vertex : adjacency.get(i)) {
				connectedComponents.set(getRootParent(vertex), getRootParent(i));
			}
		}
	}

	public void findBridges() {
		bridges.clear();
		int[] depth = new int[adjacency.size()];
		int[] minDepth = new int[adjacency.size()];

		for (int i = 0; i < adjacency.size(); i++) {
			depth[i] = -1;
			minDepth[i] = -1;
		}

		for (int i = 0; i < adjacency.size(); i++) {
			if (depth[i] == -1)
				findBridges(i, i, 0, depth, minDepth);
		}
	}

	private void findBridges(final int parentVertex, final int vertex, int currDepth, int[] depth, int[] minDepth) {
		depth[vertex] = currDepth++;
		minDepth[vertex] = depth[vertex];

		for (Integer connectedVertex : adjacency.get(vertex)) {
			if (depth[connectedVertex] == -1) {
				findBridges(vertex, connectedVertex, currDepth, depth, minDepth);
				minDepth[vertex] = Math.min(minDepth[vertex], minDepth[connectedVertex]);
				if (minDepth[connectedVertex] == depth[connectedVertex]) {
					int[] bridge = { vertex, connectedVertex };
					bridges.add(bridge);
				}
			}

			if (depth[connectedVertex] > -1 && parentVertex != connectedVertex)
				minDepth[vertex] = Math.min(minDepth[vertex], depth[connectedVertex]);
		}
	}

	public void findConnectedComponentsWithNoBridges() {
		int[] depth = new int[adjacency.size()];
		int[] minDepth = new int[adjacency.size()];

		for (int i = 0; i < adjacency.size(); i++) {
			depth[i] = -1;
			minDepth[i] = -1;
		}

		for (int i = 0; i < connCompWithNoBridges.size(); i++) {
			connCompWithNoBridges.set(i, connectedComponents.get(i));
		}

		for (int i = 0; i < adjacency.size(); i++) {
			if (depth[i] == -1)
				findConnectedComponentsWithNoBridges(i, i, 0, depth, minDepth);
		}
	}

	private void findConnectedComponentsWithNoBridges(final int parentVertex, int vertex, int currDepth, int[] depth, int[] minDepth) {
		depth[vertex] = currDepth++;
		minDepth[vertex] = depth[vertex];

		for (Integer connectedVertex : adjacency.get(vertex)) {
			if (depth[connectedVertex] == -1) {
				findConnectedComponentsWithNoBridges(vertex, connectedVertex, currDepth, depth, minDepth);
				minDepth[vertex] = Math.min(minDepth[vertex], minDepth[connectedVertex]);
				if (minDepth[connectedVertex] == depth[connectedVertex]) {
					connCompWithNoBridges.set(connectedVertex, connectedVertex);
				}
			}

			if (depth[connectedVertex] > -1 && parentVertex != connectedVertex)
				minDepth[vertex] = Math.min(minDepth[vertex], depth[connectedVertex]);
		}
	}

	public void makeForest() {
		forest.clear();
		findConnectedComponents();
		findConnectedComponentsWithNoBridges();
		
		for (int i = 0; i < connectedComponents.size(); i++) {
			if(!forest.containsKey(connectedComponents.get(i)))
				forest.put(connectedComponents.get(i), new HashMap<Integer, List<Integer>>());
			
			if(!forest.get(connectedComponents.get(i)).containsKey(connCompWithNoBridges.get(i)))
				forest.get(connectedComponents.get(i)).put(connCompWithNoBridges.get(i), new ArrayList<Integer>());
			
			forest.get(connectedComponents.get(i)).get(connCompWithNoBridges.get(i)).add(i);
		}
	}

	private int getRootParent(int v) {
		if (connectedComponents.get(v) == v)
			return v;
		return getRootParent(connectedComponents.get(v));
	}
}
