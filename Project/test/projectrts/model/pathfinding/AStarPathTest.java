package projectrts.model.pathfinding;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectrts.model.pathfinding.AStarPath;
import projectrts.model.utils.Position;

public class AStarPathTest {

	@Test
	public void testPath() {
		Position p1 = new Position(2.7, 2);
		Position p2 = new Position(6.2, 4.9);
		Node node1 = new Node(p1);
		Node node2 = new Node(p2);
		AStarNode aNode1 = new AStarNode(node1);
		AStarNode aNode2 = new AStarNode(node2);
		AStarPath path = new AStarPath();
		
		assertTrue(path.nrOfNodesLeft() == 0);
		path.addNodeToPath(aNode1);
		assertTrue(path.getNextNode().equals(p1));
		assertTrue(path.nrOfNodesLeft() == 1);
		path.addNodeToPath(aNode2);
		assertTrue(path.getNextNode().equals(p2));
		assertTrue(path.nrOfNodesLeft() == 2);
		path.removeNodeFromPath();
		assertTrue(path.getNextNode().equals(p1));
		assertTrue(path.nrOfNodesLeft() == 1);
	}
}
