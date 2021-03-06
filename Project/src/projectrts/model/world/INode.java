package projectrts.model.world;

import java.util.List;

/**
 * An interface for a node.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public interface INode {

	/**
	 * @return Is occupied
	 */
	boolean isOccupied();

	/**
	 * @param occupyingEntityID
	 *            ID of occupying entity.
	 * @return Is occupied
	 */
	boolean isOccupied(int occupyingEntityID);

	/**
	 * @return Position
	 */
	Position getPosition();

	/**
	 * @param occupyingEntityID
	 *            ID of occupying entity. Set the node unoccupied by providing 0
	 *            as parameter.
	 */
	void setOccupied(int occupyingEntityID);

	/**
	 * Returns a list of all nodes that are neighbours (connected) to this node.
	 * 
	 * @return List of the node's neighbours.
	 */
	List<INode> getNeighbours();

	/**
	 * Adds a new neighbour (connection) to this node.
	 * 
	 * @param node
	 *            The new neighbour.
	 */
	void addNeighbour(INode node);

	/**
	 * @return Cost
	 */
	float getCost();
}