package projectrts.model.core;

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
		Node startNode = world.getNodeAt(startPos);
		Node endNode = world.getNodeAt(targetPos);
	}
}
