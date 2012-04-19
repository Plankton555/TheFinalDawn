package projectrts.model.pathfinding;

import java.util.Stack;

import projectrts.model.utils.Position;

/**
 * A class representing an A* movement path. This class uses a stack for storing the nodes.
 * @author Bjorn Persson Mattsson
 *
 */
public class AStarPath {
	
	private Stack<Position> nodeStack = new Stack<Position>();
	
	/**
	 * Adds a node to the path.
	 * @param position The position that will be added.
	 */
	public void addPosToPath(Position position)
	{
		nodeStack.push(position);
	}
	
	/**
	 * @return Next position in the path (stack).
	 */
	public Position getNextPosition()
	{
		return nodeStack.peek();
	}
	
	/**
	 * Removes the next node in the path from it.
	 */
	public void removeNodeFromPath()
	{
		nodeStack.pop();
	}
	
	/**
	 * @return The number of nodes in the path.
	 */
	public int nrOfNodesLeft()
	{
		return nodeStack.size();
	}
	
	// TODO Plankton: Create a method for adding two paths into one. Could be useful... maybe.
}
