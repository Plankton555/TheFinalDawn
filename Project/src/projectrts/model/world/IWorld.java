package projectrts.model.world;

import java.util.List;

/**
 * An interface for a world class.
 * @author Bjorn P Mattsson
 *
 */
public interface IWorld {

	/**
	 * @return Width of the world (in tiles).
	 */
	public int getWorldWidth();
	
	/**
	 * @return Height of the world (in tiles).
	 */
	public int getWorldHeight();

	/**
	 * @return The matrix of all nodes.
	 */
	public INode[][] getNodes();

	/**
	 * Returns the node closest to the specified position.
	 * @param p Position.
	 * @return Node at position.
	 */
	public INode getNodeAt(Position p);

	/**
	 * Sets the nodes around nodeInCenter as occupied by entityID.
	 * @param nodeInCenter The node in center of the occupied nodes.
	 * @param entitySize The size around the center node that will be occupied.
	 * @param entityID ID of the entity that occupies.
	 */
	public void setNodesOccupied(INode nodeInCenter, float entitySize, int entityID);

	/**
	 * Returns the nodes that would be covered by an object at
	 * the provided position with the provided size.
	 * @param centerPos Center position.
	 * @param size Size.
	 * @return All nodes that would be covered.
	 */
	public List<INode> getNodesAt(Position centerPos, float size);
}
