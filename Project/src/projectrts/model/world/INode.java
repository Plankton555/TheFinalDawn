package projectrts.model.world;

import java.util.List;

import projectrts.model.utils.Position;

public interface INode {
	
	/**
	 * @return Is occupied
	 */
	public boolean isOccupied();
	
	/**
	 * @param occupyingEntityID ID of occupying entity.
	 * @return Is occupied
	 */
	public boolean isOccupied(int occupyingEntityID);
	
	/**
	 * @return Position
	 */
	public Position getPosition();

	/**
	 * @param occupyingEntityID ID of occupying entity. Set the node unoccupied by providing 0 as parameter.
	 */
	public void setOccupied(int occupyingEntityID);
	
	/**
	 * Returns a list of all nodes that are neighbours (connected) to this node.
	 * @return List of the node's neighbours.
	 */
	public List<INode> getNeighbours();
	
	/**
	 * Adds a new neighbour (connection) to this node.
	 * @param node The new neighbour.
	 */
	public void addNeighbour(INode node);
	
	/**
	 * @return Cost
	 */
	public float getCost();
}
