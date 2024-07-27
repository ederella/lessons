package decisionTree;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DecisionTreeTest {

	@Test
	final void testCreate() throws Exception {
		DecisionTree tree = new DecisionTree();
	
		tree.create("input.csv");
		
		assertTrue(tree.getTree().name.equals("Outlook"));
		assertTrue(tree.getTree().children.get(0).name.equals("Humidity"));
		assertTrue(tree.getTree().children.get(0).children.get(0).name.equals("Play Tennis"));
		assertTrue(tree.getTree().children.get(0).children.get(0).decision.equals("No"));
		assertTrue(tree.getTree().children.get(0).children.get(1).name.equals("Play Tennis"));
		assertTrue(tree.getTree().children.get(0).children.get(1).decision.equals("Yes"));
		assertTrue(tree.getTree().children.get(1).name.equals("Play Tennis"));
		assertTrue(tree.getTree().children.get(1).decision.equals("Yes"));
		assertTrue(tree.getTree().children.get(2).name.equals("Wind"));
		assertTrue(tree.getTree().children.get(2).children.get(0).name.equals("Play Tennis"));
		assertTrue(tree.getTree().children.get(2).children.get(0).decision.equals("Yes"));
		assertTrue(tree.getTree().children.get(2).children.get(1).name.equals("Play Tennis"));
		assertTrue(tree.getTree().children.get(2).children.get(1).decision.equals("No"));
	}

}
