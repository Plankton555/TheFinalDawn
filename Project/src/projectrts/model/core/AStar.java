package projectrts.model.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A* pathfinding algorithm.
 * @author Bjorn Persson Mattsson
 *
 */
public class AStar {

	private static AStar instance;
	private World world;
	
	private AStar(World world)
	{
		this.world = world;
	}
	
	/**
	 * Initializes the singleton A* class
	 * @param world Game world
	 */
	public static void initialize(World world)
	{
		instance = new AStar(world);
	}
	
	/**
	 * @return Singleton instance.
	 * @throws IllegalStateException If not yet initialized.
	 */
	public static AStar getInstance() throws IllegalStateException
	{
		if (instance == null)
		{
			throw new IllegalStateException("You must initialize this class before you can use it");
		}
		return instance;
	}
	
	public void calculatePath(Position startPos, Position targetPos)
	{
		AStarNode startNode = new AStarNode(world.getNodeAt(startPos));
		AStarNode endNode = new AStarNode(world.getNodeAt(targetPos));
		List<AStarNode> openList = new ArrayList<AStarNode>();
		List<AStarNode> closedList = new ArrayList<AStarNode>();
		
		// A* algorithm starts here
		openList.add(startNode);
		while (openList.size() > 0)
		{
			Collections.sort(openList);
			AStarNode currentNode = openList.get(0);
			
			if (currentNode.sameNodeAs(endNode))
			{
				// path complete
			}
			else
			{
				openList.remove(currentNode);
				closedList.add(currentNode);
				
				List<AStarNode> adjecentNodes = currentNode.getNeighbours();
				for (AStarNode node : adjecentNodes)
				{
					if (!openList.contains(node))
					{
						if (!closedList.contains(node))
						{
							if (!node.isObstacle())
							{
								openList.add(node);
								node.calculateCost(currentNode, endNode);
							}
						}
					}
				}
			}
		}
	}
}
