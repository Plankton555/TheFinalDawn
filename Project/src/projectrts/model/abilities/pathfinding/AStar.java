package projectrts.model.abilities.pathfinding;

import projectrts.model.world.IWorld;
import projectrts.model.world.Position;

/**
 * A* pathfinding algorithm.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public final class AStar {

	private static IWorld world;

	private AStar() { } // Empty private constructor to disallow any AStar instances.

	/**
	 * Initializes the A* class
	 * 
	 * @param world
	 *            Game world
	 */
	public static void initialize(IWorld world) {
		AStar.world = world;
	}

	/**
	 * @return true if AStar is initialized, otherwise false.
	 */
	public static boolean isInitialized() {
		return (world != null);
	}

	/**
	 * Returns the closest node that is not occupied.
	 * 
	 * @param startingPos
	 *            The position that is the starting point for the calculation.
	 * @param towards
	 *            The position that this method "strives towards". Use null if
	 *            you just want to search around the starting point in no
	 *            particular direction.
	 * @param occupyingEntityID
	 *            The entity ID of the unit which occupation is to be ignored.
	 *            Use 0 to only search for completely unoccupied nodes.
	 * @return The AStarNode which is the closest unoccupied node according to
	 *         the premises. N.B.: If no such node is found, null will be
	 *         returned.
	 */
	public static AStarNode getClosestUnoccupiedNode(Position startingPos,
			Position towards, int occupyingEntityID) {
		checkInit();
		return AStarCalculator.getClosestUnoccupiedNode(startingPos, towards,
				occupyingEntityID, world);
	}

	/**
	 * Calculates a path using the A* algorithm and sends it to the AStarUser.
	 * 
	 * @param startPos
	 *            Start position.
	 * @param targetPos
	 *            End position.
	 * @param heuristicModifier
	 *            Default is 10. A high heuristic modifier results in faster A*
	 *            calculations but a more inaccurate path. A lower modifier
	 *            results in slower calculations but a more accurate and shorter
	 *            path.
	 * @param occupyingEntityID
	 *            ID of occupying entity.
	 * @param astarUser
	 *            The AStarUser that will receive the path when it's calculated.
	 * @return An AStarPath from startPos to targetPos.
	 */
	public static void calculatePath(Position startPos, Position targetPos,
			int heuristicModifier, int occupyingEntityID, AStarUser astarUser) {
		checkInit();
		AStarCalculator pathCalculator = new AStarCalculator(startPos,
				targetPos, heuristicModifier, occupyingEntityID, astarUser,
				world);
		new Thread(pathCalculator).start();
	}

	private static void checkInit() {
		if (!isInitialized()) {
			throw new IllegalStateException(
					"You must initialize the AStar class before you can use it");
		}
	}
}