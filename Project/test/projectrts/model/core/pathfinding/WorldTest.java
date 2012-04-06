package projectrts.model.core.pathfinding;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import projectrts.model.core.Position;

public class WorldTest {

	private static World world;
	private static int width;
	private static int height;
	private Position p;
	
	@BeforeClass
	public static void beforeClass()
	{
		int width = 15;
		int height = 15;
		world = new World(height, width);
		WorldTest.width = width;
		WorldTest.height = height;
	}

	@Test
	public void testGetNodes() {
		Node[][] nodes = world.getNodes();
		
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
		Node node = world.getNodeAt(p);
		Node[][] nodeMatrix = world.getNodes();
		List<Node> allNodes = new ArrayList<Node>();
		Comparator<Node> comparatorDistance = new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
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
		
		// TODO Test illegal positions!
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
