package projectrts.model.pathfinding;

import projectrts.model.utils.Position;

public interface INode {
	
	/**
	 * @return Is occupied
	 */
	public boolean isOccupied();
	
	/**
	 * @return Position
	 */
	public Position getPosition();

}
