package projectrts.model.core.pathfinding;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectrts.model.pathfinding.AStarPath;
import projectrts.model.utils.Position;

public class AStarPathTest {

	@Test
	public void testPath() {
		Position p1 = new Position(2.7, 2);
		Position p2 = new Position(6.2, 4.9);
		AStarPath path = new AStarPath();
		
		assertTrue(path.nrOfNodesLeft() == 0);
		path.addPosToPath(p1);
		assertTrue(path.getNextPosition().equals(p1));
		assertTrue(path.nrOfNodesLeft() == 1);
		path.addPosToPath(p2);
		assertTrue(path.getNextPosition().equals(p2));
		assertTrue(path.nrOfNodesLeft() == 2);
		path.removeNodeFromPath();
		assertTrue(path.getNextPosition().equals(p1));
		assertTrue(path.nrOfNodesLeft() == 1);
	}
}
