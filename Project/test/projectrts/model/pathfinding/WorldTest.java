package projectrts.model.pathfinding;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import projectrts.model.pathfinding.Node;
import projectrts.model.pathfinding.World;
import projectrts.model.utils.Position;

public class WorldTest {

	private static World world;
	private static int width;
	private static int height;
	private Position p;
	// TODO Plankton: !!Update tests
	
	@BeforeClass
	public static void beforeClass()
	{
		int width = 15;
		int height = 15;
		world = World.getInstance();
		world.initializeWorld(height, width);
		WorldTest.width = width;
		WorldTest.height = height;
	}

	@Test
	public void testGetNodes() {
		INode[][] nodes = world.getNodes();
		
		assertTrue(nodes.length == height);
		for (int i = 0; i < nodes.length; i++)
		{
			assertTrue(nodes[i].length == width);
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
		// TODO Plankton: !!IMPLEMENT!
	}
	
	@Test
	public void testGetNodesAt()
	{
		// TODO Plankton: !!IMPLEMENT!
	}
	
	@Test
	public void testIsAnyNodeOccupied()
	{
		// TODO Plankton: !!IMPLEMENT!
	}
	
	@Test
	public void testIsAdjecentTo()
	{
		INode node1 = new Node(0,0);
		INode node2 = new Node(1,0);
		INode node3 = new Node(2,0);
		INode node4 = new Node(3,0);
		List<INode> nodes = new ArrayList<INode>();
		nodes.add(node2);
		nodes.add(node3);
		nodes.add(node4);
		
		assertTrue(!World.isAdjacentTo(node1, nodes));
		node1.addNeighbour(node4);
		assertTrue(World.isAdjacentTo(node1, nodes));
	}

	/*
	public void testCorrectNeighbours()
	{
		World world = new World(10, 10);
		Node[][] myNodes = world.getNodes();
		
		int x = 5;
		int y = 5;
		System.out.println(myNodes[y][x].getPosition());
		System.out.println("has the neighbours:");
		
		for (Node n : myNodes[y][x].getNeighbours())
		{
			System.out.println(n.getPosition());
		}
	}
	*/
}
