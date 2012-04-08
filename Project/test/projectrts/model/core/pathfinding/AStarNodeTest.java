package projectrts.model.core.pathfinding;

import static org.junit.Assert.*;

import org.junit.Test;

import projectrts.model.core.Position;

public class AStarNodeTest {

	// TODO Plankton: Test A*-node!

	@Test
	public void testGetNeighbours() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateCost() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCostFromStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalCost() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPosition() {
		Position p1 = new Position(2.7, 2.14);
		Position p2 = new Position(6.2, 4.9);
		Node node = new Node(p1);
		AStarNode aNode = new AStarNode(node);
		
		assertTrue(aNode.getPosition().equals(p1));
		assertTrue(!aNode.getPosition().equals(p2));
	}

	@Test
	public void testGetParent() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		Position p1 = new Position(2.7, 2);
		Position p2 = new Position(6.2, 4.9);
		Position p3 = new Position(2.7, 2);
		Node node1 = new Node(p1);
		Node node2 = new Node(p2);
		Node node3 = new Node(p3);
		AStarNode aNode1 = new AStarNode(node1);
		AStarNode aNode2 = new AStarNode(node2);
		AStarNode aNode3 = new AStarNode(node3);
		
		assertTrue(!aNode1.equals(aNode2));
		assertTrue(!aNode2.equals(aNode1));
		
		assertTrue(aNode1.equals(aNode3));
		assertTrue(aNode3.equals(aNode1));
		
		assertTrue(!aNode2.equals(aNode3));
		assertTrue(!aNode3.equals(aNode2));
	}

	@Test
	public void testIsObstacle() {
		fail("Not yet implemented");
	}

}
