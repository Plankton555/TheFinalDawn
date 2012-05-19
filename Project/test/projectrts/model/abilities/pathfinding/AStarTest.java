package projectrts.model.abilities.pathfinding;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectrts.model.world.INode;
import projectrts.model.world.IWorld;
import projectrts.model.world.Node;
import projectrts.model.world.Position;
import projectrts.model.world.World;

public class AStarTest {

	private AStarNode node = null;

	@Test
	public void testInitialize() {
		AStar.initialize(null);
		assertTrue(!AStar.isInitialized());
		try {
			AStar.calculatePath(null, null, 0, 0, null);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		AStar.initialize(World.INSTANCE);
		assertTrue(AStar.isInitialized());
	}

	@Test
	public void testGetClosestUnoccupiedNode() {
		World.INSTANCE.initializeWorld();
		IWorld world = World.INSTANCE;
		INode occupied = world.getNodeAt(new Position(5, 5));
		occupied.setOccupied(5);
		INode unoccupied = world.getNodeAt(new Position(5, 6));
		unoccupied.setOccupied(0);
		AStar.initialize(world);

		AStarNode closestNode = AStar.getClosestUnoccupiedNode(new Position(5,
				5), null, 0);
		assertTrue(!closestNode.getNode().equals(occupied));

		closestNode = AStar.getClosestUnoccupiedNode(new Position(5, 6), null,
				0);
		assertTrue(closestNode.getNode().equals(unoccupied));

		closestNode = AStar.getClosestUnoccupiedNode(new Position(5, 5),
				new Position(5, 7), 0);
		assertTrue(closestNode.getNode().equals(unoccupied));
	}

	@Test
	public void testCalculatePath() {
		World.INSTANCE.initializeWorld();
		IWorld world = World.INSTANCE;
		AStar.initialize(world);
		Position startPos = new Position(5.5, 5.5);
		Position targetPos = new Position(15.5, 20.5);
		AStarNode endNode = new AStarNode(new Node(targetPos));

		AStarUser astarUser = new AStarUser() {

			@Override
			public void receivePath(AStarPath path) {
				AStarNode nextNode = null;
				while (!path.isEmpty()) {
					nextNode = path.getNextNode();
					path.removeNodeFromPath();
				}
				node = nextNode;
			}
		};
		AStar.calculatePath(startPos, targetPos, 10, 1, astarUser);
		while (node == null) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// do nothing
			}
			// wait for path to be received
		}
		assertTrue(node.equals(endNode));
	}
}