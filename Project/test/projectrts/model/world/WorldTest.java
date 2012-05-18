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
	public static void beforeClass()
	{
		world = World.INSTANCE;
		world.initializeWorld();
	}

	@Test
	public void testGetNodes() {
		INode[][] nodes = world.getNodes();
		
		assertTrue(nodes.length == world.getWorldHeight());
		for (int i = 0; i < nodes.length; i++)
		{
			assertTrue(nodes[i].length == world.getWorldWidth());
			for (int j = 0; j < nodes[i].length; j++)
			{
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
				double dist1 = Position.getVectorBetween(p, o1.getPosition()).length();
				double dist2 = Position.getVectorBetween(p, o2.getPosition()).length();
				int result = Double.compare(dist1, dist2);
				if (result == 0) // if distance is the same, round upwards to get a clear result
				{
					result = -1;
				}
				return result;
			}
		};
		
		for (int i = 0; i < nodeMatrix.length; i++)
		{
			for (int j = 0; j < nodeMatrix[i].length; j++)
			{
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
	public void testSetNodesOccupied()
	{
		// TODO Plankton: Implement
		IWorld world = World.INSTANCE;
		//world.setNodesOccupied(nodeInCenter, entitySize, entityID);
	}
	
	@Test
	public void testGetNodesAt()
	{
		// TODO Plankton: Implement
	}
}
