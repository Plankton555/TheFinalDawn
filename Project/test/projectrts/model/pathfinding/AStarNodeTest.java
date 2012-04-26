package projectrts.model.pathfinding;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import projectrts.model.pathfinding.AStarNode;
import projectrts.model.pathfinding.Node;
import projectrts.model.utils.Position;

public class AStarNodeTest {

	private static Position p1;
	private static Position p2;
	private static Position p3;
	private static Position p4;
	private static Position p5;
	private static Position p6;
	private static Position p7;
	private static Position p8;
	private static Position p9;
	private static Position p10;
	private static Node node1;
	private static Node node2;
	private static Node node3;
	private static Node node4;
	private static Node node5;
	private static Node node6;
	private static Node node7;
	private static Node node8;
	private static Node node9;
	private static Node node10;
	private static AStarNode aNode1;
	private static AStarNode aNode2;
	private static AStarNode aNode3;
	private static AStarNode aNode4;
	private static AStarNode aNode5;
	private static AStarNode aNode6;
	private static AStarNode aNode7;
	private static AStarNode aNode8;
	private static AStarNode aNode9;
	private static AStarNode aNode10;
	
	@BeforeClass
	public static void beforeClass()
	{
		p1 = new Position(2.7, 2);
		p2 = new Position(6.2, 4.9);
		p3 = new Position(2.7, 2);
		p4 = new Position(2.7, 5);
		p5 = new Position(2, 2);
		p6 = new Position(2, 6);
		p7 = new Position(5, 5);
		p8 = new Position(2, 3);
		p9 = new Position(1, 2);
		p10 = new Position(1, 2);
		node1 = new Node(p1);
		node2 = new Node(p2);
		node3 = new Node(p3);
		node4 = new Node(p4);
		node5 = new Node(p5);
		node6 = new Node(p6);
		node7 = new Node(p7);
		node8 = new Node(p8);
		node9 = new Node(p9);
		node10 = new Node(p10);
		aNode1 = new AStarNode(node1);
		aNode2 = new AStarNode(node2);
		aNode3 = new AStarNode(node3);
		aNode4 = new AStarNode(node4);
		aNode5 = new AStarNode(node5);
		aNode6 = new AStarNode(node6);
		aNode7 = new AStarNode(node7);
		aNode8 = new AStarNode(node8);
		aNode9 = new AStarNode(node9);
		aNode10 = new AStarNode(node10);
	}
	
	@Test
	public void testGetNeighbours() {
		assertTrue(aNode1.getNeighbours().size() == 0);
		node1.addNeighbour(node2);
		List<AStarNode> neighbours = aNode1.getNeighbours();
		assertTrue(neighbours.contains(aNode2));
		assertTrue(!neighbours.contains(aNode4));
		node1.addNeighbour(node4);
		neighbours = aNode1.getNeighbours();
		assertTrue(neighbours.contains(aNode4));
	}

	@Test
	public void testCalculateHeuristic() {
		
		aNode5.calculateHeuristic(aNode6);
		assertTrue(aNode5.getHeuristic() == 40);
		aNode6.calculateHeuristic(aNode5);
		assertTrue(aNode6.getHeuristic() == 40);
		aNode5.calculateHeuristic(aNode7);
		assertTrue(aNode5.getHeuristic() == 60);
		aNode7.calculateHeuristic(aNode6);
		assertTrue(aNode6.getHeuristic() == 40);
	}

	@Test
	public void testCalculateCostFromStart() {
		
		assertTrue(aNode5.getCostFromStart() == 0);
		aNode8.calculateCostFromStart(aNode5, false);
		assertTrue(aNode8.getCostFromStart() == 10);
		aNode9.calculateCostFromStart(aNode8, false);
		assertTrue(aNode9.getCostFromStart() == 24);
		aNode9.calculateCostFromStart(aNode5, false);
		assertTrue(aNode9.getCostFromStart() == 10);
		aNode9.calculateCostFromStart(aNode8, true);
		assertTrue(aNode9.getCostFromStart() == 10);
		aNode9.calculateCostFromStart(aNode8, false);
		assertTrue(aNode9.getCostFromStart() == 24);
	}

	@Test
	public void testGetPosition() {
		assertTrue(aNode1.getPosition().equals(p1));
		assertTrue(!aNode1.getPosition().equals(p2));
	}

	@Test
	public void testGetParent() {
		
		assertTrue(aNode5.getParent() == null);
		aNode8.calculateCostFromStart(aNode5, false);
		assertTrue(aNode8.getParent().equals(aNode5));
		aNode9.calculateCostFromStart(aNode8, false);
		assertTrue(aNode9.getParent().equals(aNode8));
		aNode9.calculateCostFromStart(aNode5, false);
		assertTrue(aNode9.getParent().equals(aNode5));
	}

	@Test
	public void testCompareTo() {
		
		aNode8.calculateCostFromStart(aNode5, false);
		aNode9.calculateCostFromStart(aNode8, false);
		assertTrue(aNode9.compareTo(aNode8) > 0);
		assertTrue(aNode8.compareTo(aNode9) < 0);
	}
	
	@Test
	public void testHashCode()
	{
		assertTrue(aNode10.hashCode() == aNode9.hashCode());
		assertTrue(aNode10.hashCode() == aNode10.hashCode());
		assertTrue(aNode8.hashCode() != aNode9.hashCode());
		assertTrue(aNode10.hashCode() != new AStarNode(null).hashCode());
	}

	@Test
	public void testEquals() {
		assertTrue(!aNode1.equals(aNode2));
		assertTrue(!aNode2.equals(aNode1));
		
		assertTrue(aNode1.equals(aNode3));
		assertTrue(aNode3.equals(aNode1));
		
		assertTrue(!aNode2.equals(aNode3));
		assertTrue(!aNode3.equals(aNode2));
	}

	@Test
	public void testIsObstacle() {
		node1.setOccupied(1);
		assertTrue(aNode1.isObstacle(2));
		node1.setOccupied(0);
		assertTrue(!aNode1.isObstacle(2));
	}

}
