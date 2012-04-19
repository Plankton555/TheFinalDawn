package projectrts.model.core.pathfinding;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import projectrts.model.pathfinding.AStarNode;
import projectrts.model.pathfinding.Node;
import projectrts.model.utils.Position;

public class AStarNodeTest {

	@Test
	public void testGetNeighbours() {
		Position p1 = new Position(2.7, 2);
		Position p2 = new Position(6.2, 4.9);
		Position p3 = new Position(2.7, 5);
		Node node1 = new Node(p1);
		Node node2 = new Node(p2);
		Node node3 = new Node(p3);
		AStarNode aNode1 = new AStarNode(node1);
		AStarNode aNode2 = new AStarNode(node2);
		AStarNode aNode3 = new AStarNode(node3);
		
		assertTrue(aNode1.getNeighbours().size() == 0);
		node1.addNeighbour(node2);
		List<AStarNode> neighbours = aNode1.getNeighbours();
		assertTrue(neighbours.contains(aNode2));
		assertTrue(!neighbours.contains(aNode3));
		node1.addNeighbour(node3);
		neighbours = aNode1.getNeighbours();
		assertTrue(neighbours.contains(aNode3));
	}

	@Test
	public void testCalculateHeuristic() {
		Position p1 = new Position(2, 2);
		Position p2 = new Position(2, 6);
		Position p3 = new Position(5, 5);
		Node node1 = new Node(p1);
		Node node2 = new Node(p2);
		Node node3 = new Node(p3);
		AStarNode aNode1 = new AStarNode(node1);
		AStarNode aNode2 = new AStarNode(node2);
		AStarNode aNode3 = new AStarNode(node3);
		
		aNode1.calculateHeuristic(aNode2);
		assertTrue(aNode1.getHeuristic() == 40);
		aNode2.calculateHeuristic(aNode1);
		assertTrue(aNode2.getHeuristic() == 40);
		aNode1.calculateHeuristic(aNode3);
		assertTrue(aNode1.getHeuristic() == 60);
		aNode3.calculateHeuristic(aNode2);
		assertTrue(aNode2.getHeuristic() == 40);
	}

	@Test
	public void testCalculateCostFromStart() {
		Position p1 = new Position(2, 2);
		Position p2 = new Position(2, 3);
		Position p3 = new Position(1, 2);
		Node node1 = new Node(p1);
		Node node2 = new Node(p2);
		Node node3 = new Node(p3);
		AStarNode aNode1 = new AStarNode(node1);
		AStarNode aNode2 = new AStarNode(node2);
		AStarNode aNode3 = new AStarNode(node3);
		
		assertTrue(aNode1.getCostFromStart() == 0);
		aNode2.calculateCostFromStart(aNode1, false);
		assertTrue(aNode2.getCostFromStart() == 10);
		aNode3.calculateCostFromStart(aNode2, false);
		assertTrue(aNode3.getCostFromStart() == 24);
		aNode3.calculateCostFromStart(aNode1, false);
		assertTrue(aNode3.getCostFromStart() == 10);
		aNode3.calculateCostFromStart(aNode2, true);
		assertTrue(aNode3.getCostFromStart() == 10);
		aNode3.calculateCostFromStart(aNode2, false);
		assertTrue(aNode3.getCostFromStart() == 24);
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
		Position p1 = new Position(2, 2);
		Position p2 = new Position(2, 3);
		Position p3 = new Position(1, 2);
		Node node1 = new Node(p1);
		Node node2 = new Node(p2);
		Node node3 = new Node(p3);
		AStarNode aNode1 = new AStarNode(node1);
		AStarNode aNode2 = new AStarNode(node2);
		AStarNode aNode3 = new AStarNode(node3);
		
		assertTrue(aNode1.getParent() == null);
		aNode2.calculateCostFromStart(aNode1, false);
		assertTrue(aNode2.getParent().equals(aNode1));
		aNode3.calculateCostFromStart(aNode2, false);
		assertTrue(aNode3.getParent().equals(aNode2));
		aNode3.calculateCostFromStart(aNode1, false);
		assertTrue(aNode3.getParent().equals(aNode1));
	}

	@Test
	public void testCompareTo() {
		Position p1 = new Position(2, 2);
		Position p2 = new Position(2, 3);
		Position p3 = new Position(1, 2);
		Node node1 = new Node(p1);
		Node node2 = new Node(p2);
		Node node3 = new Node(p3);
		AStarNode aNode1 = new AStarNode(node1);
		AStarNode aNode2 = new AStarNode(node2);
		AStarNode aNode3 = new AStarNode(node3);
		
		aNode2.calculateCostFromStart(aNode1, false);
		aNode3.calculateCostFromStart(aNode2, false);
		assertTrue(aNode3.compareTo(aNode2) > 0);
		assertTrue(aNode2.compareTo(aNode3) < 0);
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
		Position p = new Position(2.7, 2);
		Node node = new Node(p);
		AStarNode aNode = new AStarNode(node);
		
		node.setOccupied(1);
		assertTrue(aNode.isObstacle(2));
		node.setOccupied(0);
		assertTrue(!aNode.isObstacle(2));
	}

}
