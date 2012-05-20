package projectrts.model.abilities.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import projectrts.model.world.IWorld;
import projectrts.model.world.Position;

class AStarCalculator implements Runnable {
	private final Position startPos;
	private final Position targetPos;
	private final int heuristicModifier;
	private final int occupyingEntityID;
	private final AStarUser astarUser;
	private final IWorld world;

	/**
	 * Creates a path calculator with start parameters.
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
	 */
	public AStarCalculator(Position startPos, Position targetPos,
			int heuristicModifier, int occupyingEntityID, AStarUser astarUser,
			IWorld world) {
		this.startPos = startPos;
		this.targetPos = targetPos;
		this.heuristicModifier = heuristicModifier;
		this.occupyingEntityID = occupyingEntityID;
		this.astarUser = astarUser;
		this.world = world;
	}

	@Override
	public void run() {
		AStarNode startNode = new AStarNode(world.getNodeAt(startPos));
		AStarNode endNode = new AStarNode(world.getNodeAt(targetPos));
		List<AStarNode> openList = new ArrayList<AStarNode>();
		List<AStarNode> closedList = new ArrayList<AStarNode>();
		// Sets the search limit to 10 times the approximate distance to the
		// target (or 50 if the distance is short)
		startNode.calculateHeuristic(endNode, 10);
		int searchlimit = Math.max(startNode.getHeuristic(), 50);

		if (endNode.isObstacle(occupyingEntityID)) {
			endNode = getClosestUnoccupiedNode(targetPos, startPos,
					occupyingEntityID, world);
		}

		// A* algorithm starts here
		openList.add(startNode);
		while (!openList.isEmpty()) // while open list is not empty
		{
			// current node = node from the open list with the lowest cost
			Collections.sort(openList);
			AStarNode currentNode = openList.get(0);

			if (currentNode.equals(endNode)) {
				astarUser.receivePath(generatePath(startNode, currentNode));
				return;
			} else {
				// move current node to the closed list
				openList.remove(0);
				closedList.add(currentNode);

				// examine each node adjacent to the current node
				List<AStarNode> adjacentNodes = currentNode.getNeighbours();
				for (AStarNode node : adjacentNodes) {
					// if not an obstacle and not on closed list
					if (!node.isObstacle(occupyingEntityID)
							&& !closedList.contains(node)) {
						// if on open list, check to see if new path is better
						if (openList.contains(node)) {
							node.calculateCostFromStart(currentNode, true);
						} else // if not on open list
						{
							// move to open list and calculate cost
							openList.add(node);
							node.calculateCostFromStart(currentNode, false);
							node.calculateHeuristic(endNode, heuristicModifier);
						}
					}
				}
			}
			// if too many nodes are searched without finding a way, break out
			// of loop.
			if (closedList.size() > searchlimit) {
				break;
			}
		}
		// path not found, return path to the node closest to the target
		// instead.

		Collections.sort(closedList, AStarNode.getHeuristicComparator());
		astarUser.receivePath(generatePath(startNode, closedList.get(0)));
	}

	private static AStarPath generatePath(AStarNode startNode, AStarNode endNode) {
		AStarPath path = new AStarPath();
		AStarNode nextNode = endNode;

		while (!nextNode.equals(startNode)) {
			path.addNodeToPath(nextNode);
			nextNode = nextNode.getParent();
		}
		return path;
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
			Position towards, int occupyingEntityID, IWorld world) {
		// Use A* algorithm to find the closest walkable node.
		AStarNode targetNode = new AStarNode(world.getNodeAt(startingPos));
		AStarNode towardsNode;

		if (towards == null) {
			towardsNode = null;
		} else {
			towardsNode = new AStarNode(world.getNodeAt(towards));
		}
		List<AStarNode> closedList = new ArrayList<AStarNode>();
		List<AStarNode> openList = new ArrayList<AStarNode>();

		openList.add(targetNode);
		while (!openList.isEmpty()) {
			Collections.sort(openList);
			AStarNode currentNode = openList.get(0);

			if (!currentNode.isObstacle(occupyingEntityID)) {
				return currentNode;
			}
			openList.remove(currentNode);
			closedList.add(currentNode);

			List<AStarNode> adjacentNodes = currentNode.getNeighbours();
			for (AStarNode node : adjacentNodes) {
				if (!openList.contains(node) && !closedList.contains(node)) {
					openList.add(node);
					node.calculateCostFromStart(currentNode, false);
					if (towardsNode != null) {
						node.calculateHeuristic(towardsNode, 1);
					}
				}
			}
		}
		// Nothing could be found
		return null;
	}
}