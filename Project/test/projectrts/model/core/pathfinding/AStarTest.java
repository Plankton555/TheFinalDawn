package projectrts.model.core.pathfinding;

import static org.junit.Assert.*;

import org.junit.Test;

public class AStarTest {

	@Test
	public void testInitialize() {
		AStar astar;
		
		try {
			astar = new AStar();
			assertTrue(false);
		} catch (IllegalStateException e) {
			assertTrue(true);
		}
		
		World world = World.getInstance();
		world.initializeWorld(5, 5);
		AStar.initialize(world);
		astar = new AStar();
		assertTrue(astar != null);
	}

	@Test
	public void testCalculatePath() {
		// TODO Plankton: No clue on how to test this in a good way...
		fail("Not yet implemented");
	}

}
