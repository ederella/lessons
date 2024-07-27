package dynamic_adjacency;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {
	static Graph g;
	@BeforeEach
	final void init() {
		g = new Graph(10);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(3, 2);
		g.addEdge(2, 4);
		g.addEdge(3, 5);		
		g.addEdge(6, 7);
		g.addEdge(6, 8);
	}

	@Test
	final void testAddEdge() {
		
		Assertions.assertIterableEquals(g.adjacency.get(0), Arrays.asList(1,2));
		Assertions.assertIterableEquals(g.adjacency.get(1), Arrays.asList(0,3));
		Assertions.assertIterableEquals(g.adjacency.get(2), Arrays.asList(0,3,4));
		Assertions.assertIterableEquals(g.adjacency.get(3), Arrays.asList(1,2,5));
		Assertions.assertIterableEquals(g.adjacency.get(4), Arrays.asList(2));
		Assertions.assertIterableEquals(g.adjacency.get(5), Arrays.asList(3));
		Assertions.assertIterableEquals(g.adjacency.get(6), Arrays.asList(7,8));
		Assertions.assertIterableEquals(g.adjacency.get(7), Arrays.asList(6));
		Assertions.assertIterableEquals(g.adjacency.get(8), Arrays.asList(6));
		Assertions.assertIterableEquals(g.adjacency.get(9), Arrays.asList());
		
		
	}

	@Test
	final void testDeleteEdge() {

		g.deleteEdge(3, 2);
		assertIterableEquals(g.adjacency.get(3), Arrays.asList(1,5));
	}

	@Test
	final void testIsConnected() {
		assertTrue(g.isConnected(0, 1));
		assertFalse(g.isConnected(0, 9));
	}

	@Test
	final void testIsConnectedDSU() {
		assertTrue(g.isConnected(0, 1));
		assertFalse(g.isConnected(0, 9));
	}

	@Test
	final void testFindConnectedComponents() {
		g.findConnectedComponents();
		assertIterableEquals(g.connectedComponents, Arrays.asList(0,0,0,0,0,0,6,6,6,9));
		
	}

	@Test
	final void testFindBridges() {
		g.findBridges();
		assertTrue(g.bridges.size() == 4);
		
	}

	@Test
	final void testFindConnectedComponentsWithNoBridges() {
		g.findConnectedComponentsWithNoBridges();
		assertIterableEquals(g.connCompWithNoBridges, Arrays.asList(0,0,0,0,4,5,6,7,8,9));
	}

	@Test
	final void testMakeForest() {
		g.makeForest();
		assertTrue(g.forest.size() == 3);
	}

}
