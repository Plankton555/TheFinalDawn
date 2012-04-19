package projectrts.model.core.pathfinding;

import projectrts.model.core.Position;
import projectrts.model.core.utils.ModelUtils;

/**
 * The class containing the "world"
 * @author Bjorn Persson Mattsson
 *
 */
public class World {
	
	private static World instance;
	private Node[][] nodes;
	private World()
	{
	}
	public static World getInstance()
	{
		if (instance == null)
		{
			instance = new World();
		}
		return instance;
	}
	
	private int width;
	private int height;
	
	/**
	 * Initializes the world with specified height and width.
	 * @param height Height.
	 * @param width Width.
	 */
	public void initializeWorld(int height, int width) {
		initNodes(height, width);
		//setTestOccupation();
	}
	
	private void initNodes(int height, int width)
	{
		nodes = new Node[height][width];
		this.width = width;
		this.height = height;
		
		// Creates a matrix of nodes with the upper left at position (0.5, 0.5)
		// and the lower right at position (width+0.5, height+0.5)
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				nodes[i][j] = new Node(j+0.5f, i+0.5f);
			}
		}
		createNodeConnections();
		// Update all nodes distance to obstacles.
	}
	
	private void createNodeConnections()
	{
		for (int i=0; i<nodes.length; i++)
		{
			for (int j=0; j<nodes[i].length; j++)
			{
				// Add all nodes surrounding this node
				for (int k=i-1; k<=i+1; k++)
				{
					// If the row exists
					if (k >= 0 && k < nodes.length)
					{
						for (int l=j-1; l<=j+1; l++)
						{
							// If the column exists
							if (l >= 0 && l < nodes[i].length)
							{
								nodes[i][j].addNeighbour(nodes[k][l]);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * @return The matrix of all nodes.
	 */
	public Node[][] getNodes()
	{
		// TODO Plankton: Return a shallow copy?
		return nodes;
	}
	
	/**
	 * Returns the node closest to the specified position.
	 * @param p Position.
	 * @return Node at position.
	 */
	public Node getNodeAt(Position p)
	{
		int x = (int)p.getX();
		int y = (int)p.getY();
		
		x = (int)ModelUtils.INSTANCE.clamp(x, 0, width-1);
		y = (int)ModelUtils.INSTANCE.clamp(y, 0, height-1);
		return nodes[y][x];
	}
	
	private void setTestOccupation()
	{
		for (int i=40; i<60; i++)
		{
			// Creates a "wall" (currently invisible) from pos (40,40) to (60,40)
			nodes[40][i].setOccupied(-1);
		}
	}
	
	/**
	 * Sets the nodes around nodeInCenter as occupied by entityID.
	 * @param nodeInCenter The node in center of the occupied nodes.
	 * @param entitySize The size around the center node that will be occupied.
	 * @param entityID ID of the entity that occupies.
	 */
	public void setNodesOccupied(Node nodeInCenter, float entitySize, int entityID) {
		// TODO Plankton: Implement this!!!
		
	}
}
