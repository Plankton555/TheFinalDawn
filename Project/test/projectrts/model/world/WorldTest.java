package projectrts.model.world;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class WorldTest {

	private static World world;
	private Position p;

	@BeforeClass
	public static void beforeClass() {
		world = World.INSTANCE;
		world.initializeWorld();
	}

	@Test
	public void testGetNodes() {
		INode[][] nodes = world.getNodes();

		assertTrue(nodes.length == world.getWorldHeight());
		for (int i = 0; i < nodes.length; i++) {
			assertTrue(nodes[i].length == world.getWorldWidth());
			for (int j = 0; j < nodes[i].length; j++) {
				assertTrue(nodes[i][j] != null);
			}
		}
	}

	@Test
	public void testGetNodeAt() {
		p = new Position(0, 0);
		INode node = world.getNodeAt(p);
		INode[][] nodeMatrix = world.getNodes();
		List<INode> allNodes = new ArrayList<INode>();
		Comparator<INode> comparatorDistance = new Comparator<INode>() {
			@Override
			public int compare(INode o1, INode o2) {
				double dist1 = Position.getVectorBetween(p, o1.getPosition())
						.length();
				double dist2 = Position.getVectorBetween(p, o2.getPosition())
						.length();
				int result = Double.compare(dist1, dist2);
				if (result == 0) // if distance is the same, round upwards to
									// get a clear result
				{
					result = -1;
				}
				return result;
			}
		};

		for (int i = 0; i < nodeMatrix.length; i++) {
			for (int j = 0; j < nodeMatrix[i].length; j++) {
				allNodes.add(nodeMatrix[i][j]);
			}
		}

		p = new Position(5, 5);
		node = world.getNodeAt(p);
		Collections.sort(allNodes, comparatorDistance);
		assertTrue(allNodes.get(0) == node);

		p = new Position(3.7, 8.13);
		node = world.getNodeAt(p);
		Collections.sort(allNodes, comparatorDistance);
		assertTrue(allNodes.get(0) == node);

		p = new Position(5, -3);
		node = world.getNodeAt(p);
		Collections.sort(allNodes, comparatorDistance);
		assertTrue(allNodes.get(0) == node);

		p = new Position(18, -3);
		node = world.getNodeAt(p);
		Collections.sort(allNodes, comparatorDistance);
		assertTrue(allNodes.get(0) == node);
	}

	@Test
	public void testSetNodesOccupied() {
		IWorld world = World.INSTANCE;
		List<INode> nodes = new ArrayList<INode>();
		INode nodeInCenter = world.getNodeAt(new Position(20.5, 20.5));
		for (int i = 18; i < 23; i++) {
			for (int j = 18; j < 23; j++) {
				nodes.add(world.getNodeAt(new Position(i + 0.5, j + 0.5)));
			}
		}
		world.setNodesOccupied(nodeInCenter, 5, 0);
		for (INode node : nodes) {
			assertTrue(!node.isOccupied());
		}

		world.setNodesOccupied(nodeInCenter, 1, 1);
		assertTrue(nodeInCenter.isOccupied());
		for (INode node : nodes) {
			if (!node.equals(nodeInCenter)) {
				assertTrue(!node.isOccupied());
			}
		}

		world.setNodesOccupied(nodeInCenter, 5, 0);
		for (INode node : nodes) {
			assertTrue(!node.isOccupied());
		}

		world.setNodesOccupied(nodeInCenter, 2, 1);
		assertTrue(nodeInCenter.isOccupied());
		for (INode node : nodeInCenter.getNeighbours()) {
			assertTrue(node.isOccupied());
		}
	}

	@Test
	public void testGetNodesAt() {
		IWorld world = World.INSTANCE;
		List<INode> nodes = world.getNodesAt(new Position(20.5, 20.5), 1);
		assertTrue(nodes.size() == 1);

		nodes = world.getNodesAt(new Position(20.5, 20.5), 2);
		assertTrue(nodes.size() == 9);

		nodes = world.getNodesAt(new Position(20.5, 20.5), 3);
		assertTrue(nodes.size() == 9);
	}
}
