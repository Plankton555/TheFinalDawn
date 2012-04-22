package projectrts.model.pathfinding;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import projectrts.model.pathfinding.AStar;
import projectrts.model.pathfinding.World;

public class AStarTest {

	@Test
	public void testInitialize() {
		AStar astar;
		
		try {
			astar = AStar.getInstance();
			assertTrue(false);
		} catch (IllegalStateException e) {
			assertTrue(true);
		}
		
		World world = World.getInstance();
		world.initializeWorld(5, 5);
		AStar.initialize(world);
		astar = AStar.getInstance();
		assertTrue(astar != null);
	}
	// TODO Plankton: Test getClosestUnoccupiedNode()!

	@Test
	public void testCalculatePath() {
		// TODO Plankton: No clue on how to test this in a good way...
		fail("Not yet implemented");
	}

}
