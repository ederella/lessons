package euler;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class EulerianTour {
	ArrayList<SimpleGraph> graphs;

	ArrayList<LinkedList<Vertex>> paths;

	public EulerianTour() {
		graphs = new ArrayList<SimpleGraph>();
		paths = new ArrayList<LinkedList<Vertex>>();
	}

	public void addGraphFromTree(SimpleTree tree) {
		graphs.add(tree.toSimpleGraph());
	}

	public void addPathFromGraph(int numberOfGraph) {
		SimpleGraph graph = graphs.get(numberOfGraph);
		
		paths.add(graph.visitAll());
		
	}

	public void setRoot(int numberOfGraph, Vertex newRoot) {
		LinkedList<Vertex> path = paths.get(numberOfGraph);
		
		LinkedList<Vertex> pathA = new LinkedList<Vertex>();
		LinkedList<Vertex> pathB = new LinkedList<Vertex>();
		
		boolean isAfterNewRoot = false;
		for (Vertex vertex : path) {
			isAfterNewRoot = isAfterNewRoot || vertex == newRoot;
			if(!isAfterNewRoot)
				pathA.add(vertex);
			if(isAfterNewRoot)
				pathB.add(vertex);
		}
		
		path.clear();
		pathA.pop();
		path.addAll(pathB);
		path.addAll(pathA);
		path.add(newRoot);
	}


	public void connect(int numberOfGraph1, Vertex v1, int numberOfGraph2, Vertex v2) {
		setRoot(numberOfGraph1, v1);
		setRoot(numberOfGraph2, v2);
		
		LinkedList<Vertex> connectedPath = new LinkedList<Vertex>();
		
		connectedPath.addAll(paths.get(numberOfGraph1));
		connectedPath.addAll(paths.get(numberOfGraph2));
		connectedPath.add(v1);
		
		paths.remove(numberOfGraph2);
		paths.set(numberOfGraph1, connectedPath);
		
		SimpleGraph g1 = graphs.get(numberOfGraph1);
		SimpleGraph g2 = graphs.get(numberOfGraph2);
		
		int v1Idx = g1.getVertexIndex(v1);
		int v2Idx = g2.getVertexIndex(v2);
		
		SimpleGraph connectedGraph = new SimpleGraph(g1.max_vertex + g2.max_vertex);
		
		for (int i = 0; i < g1.max_vertex; i++) {
			connectedGraph.vertex[i] = g1.vertex[i];
			for(int j = 0; j < g1.max_vertex; j++){
				connectedGraph.m_adjacency[i][j] = g1.m_adjacency[i][j];
			}
		}
		
		for (int i = 0; i < g2.max_vertex; i++) {
			connectedGraph.vertex[i + g1.max_vertex] = g2.vertex[i];
			for(int j = 0; j < g2.max_vertex; j++){
				connectedGraph.m_adjacency[i+ g1.max_vertex][j+ g1.max_vertex] = g2.m_adjacency[i][j];
			}
		}
		
		connectedGraph.AddEdge(v1Idx, v2Idx);
		graphs.remove(numberOfGraph2);
		graphs.set(numberOfGraph1, connectedGraph);
	}

	public boolean isConnected(Vertex v1, Vertex v2) {
		Vertex rootV1 = findRoot(v1);
		Vertex rootV2 = findRoot(v2);
		return rootV1 == rootV2;
	}

	private Vertex findRoot(Vertex v1) {
		for (LinkedList<Vertex> path : paths) {
			if(path.contains(v1)) {
				return path.peek();
			}
		}
		return null;
	}

}

class Vertex {
	public int Value;

	public boolean Hit;

	public Vertex(int val) {
		Value = val;
		Hit = false;
	}
	
	public String toString() {		
		return "["+ Value + "]";		
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

	public LinkedList<Vertex> visitAll() {
		LinkedList<Vertex> path = new  LinkedList<Vertex>();
		visitAll(0, path);
		return path;
	}

	private void visitAll(int vertexIdx, LinkedList<Vertex> path) {
		path.add(vertex[vertexIdx]);
		for(int i = vertexIdx; i < vertex.length; i++) {
			if(isEdge(vertexIdx, i)) {
				visitAll(i, path);
			}
		}	
		
		for(int i = 0; i < vertexIdx; i++) {
			if(isEdge(vertexIdx, i)) {
				path.add(vertex[i]);
				return;
			}
		}		
	}

	public int addVertex(Vertex vert) {
		int vertextCount = getCount();
		if (vertextCount < vertex.length) {
			vertex[vertextCount] = vert;
		}
		return vertextCount;
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

	public boolean isEdge(int v1, int v2) {
		return m_adjacency[v1][v2] == 1;
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

		if (isEdge(VFrom, VTo)) {
			dfsStack.push(VTo);
			return;
		}
		for (int i = 0; i < vertex.length; i++) {
			if (isEdge(VFrom, i) && !vertex[i].Hit) {
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

			if (isEdge(current, VTo)) {
				vertex[VTo].Hit = true;
				path.add(vertex[VTo]);
				removeExcessFromPath(path, bfsQueue);
				return path;
			}

			for (int i = 0; i < vertex.length; i++) {
				if (isEdge(current, i) && !vertex[i].Hit) {
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
	
	public int getVertexIndex(Vertex v) {
		for (int i = 0; i < vertex.length; i++) {
			if(v == vertex[i])
				return i;
		}
		return -1;
	}
}

class SimpleTreeNode {

	public Integer NodeValue;

	public SimpleTreeNode Parent;

	public List<SimpleTreeNode> Children;

	public SimpleTreeNode(Integer val, SimpleTreeNode parent) {

		NodeValue = val;
		Parent = parent;
		Children = null;
	}
}

class SimpleTree {

	public SimpleTreeNode Root;

	private int count;
	public SimpleTree(SimpleTreeNode root) {
		count++;
		Root = root;
	}

	public void AddChild(SimpleTreeNode ParentNode, SimpleTreeNode NewChild) {

		if (NewChild == null)
			return;

		if (ParentNode == null) {
			Root = NewChild;
			count++;
			return;

		}

		if (ParentNode.Children == null)
			ParentNode.Children = new ArrayList<SimpleTreeNode>();

		ParentNode.Children.add(NewChild);
		NewChild.Parent = ParentNode;
		count++;

	}

	public SimpleGraph toSimpleGraph() {
		if (Root == null)
			return new SimpleGraph(0);
		SimpleGraph graph = new SimpleGraph(count);
		
		toSimpleGraph(Root, graph );
		
		return graph;
	}

	private int toSimpleGraph(SimpleTreeNode node, SimpleGraph graph ) {
		
		int vertNumber1 = graph.addVertex(new Vertex(node.NodeValue));

		if (node.Children == null || node.Children.isEmpty()) {
			return vertNumber1;
		}

		for (SimpleTreeNode child : node.Children) {
			int vertNumber2 = toSimpleGraph(child, graph);
			graph.AddEdge(vertNumber1, vertNumber2);
		}

		return vertNumber1;
	}


	public int Count() {
		return count;

	}


}