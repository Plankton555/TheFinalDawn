package projectrts.model.abilities.pathfinding;

/**
 * An object that is able to receive an AStarPath.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public interface AStarUsable {

	/**
	 * @param path
	 *            The calculated AStarPath.
	 */
	void receivePath(AStarPath path);
}