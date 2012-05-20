package projectrts.model.world;

import java.util.ArrayList;
import java.util.List;

/**
 * A node with the primary purpose of helping with pathfinding.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class Node implements INode {

	private Position position;
	private int occupyingEntityID = 0;
	private float cost = 1;
	private final List<INode> neighbours = new ArrayList<INode>();

	/**
	 * Creates a new node at the position.
	 * 
	 * @param position
	 *            Position.
	 */
	public Node(Position position) {
		this(position.getX(), position.getY());
	}

	/**
	 * Creates a new node at the position of the x and y coordinates.
	 * 
	 * @param posX
	 *            X coordinate.
	 * @param posY
	 *            Y coordinate.
	 */
	public Node(double posX, double posY) {
		this.position = new Position(posX, posY);
	}

	/**
	 * @return Position of the node.
	 */
	public Position getPosition() {
		return this.position.copy();
	}

	@Override
	public boolean isOccupied() {
		return isOccupied(0);
	}

	@Override
	public boolean isOccupied(int occupyingEntityID) {
		return (!(this.occupyingEntityID == occupyingEntityID || this.occupyingEntityID == 0));
	}

	/**
	 * @param occupyingEntityID
	 *            ID of occupying entity. Set the node unoccupied by providing 0
	 *            as parameter.
	 */
	public void setOccupied(int occupyingEntityID) {
		this.occupyingEntityID = occupyingEntityID;
	}

	@Override
	public List<INode> getNeighbours() {
		List<INode> output = new ArrayList<INode>();
		for (INode n : neighbours) {
			output.add(n);
		}
		return output;
	}

	@Override
	public void addNeighbour(INode node) {
		if (!this.equals(node)) {
			neighbours.add(node);
		}
	}

	@Override
	public int hashCode() {
		// Eclipse-generated hashcode method based on Position.
		final int prime = 173;
		int result = 1;
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// Eclipse-generated equals method based on Position.
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Node other = (Node) obj;
		if (position == null) {
			if (other.position != null) {
				return false;
			}
		} else if (!position.equals(other.position)) {
			return false;
		}
		return true;
	}

	@Override
	public float getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            Cost
	 */
	public void setCost(float cost) {
		this.cost = cost;
	}

	/**
	 * Determines whether any of the provided nodes are occupied.
	 * 
	 * @param nodes
	 *            The nodes to be examined.
	 * @return true if any node is occupied, otherwise false.
	 */
	public static boolean isAnyNodeOccupied(List<INode> nodes) {
		for (INode node : nodes) {
			if (node.isOccupied()) {
				return true;
			}
		}
		return false;
	}
}