package projectrts.model.core.pathfinding;

import java.util.ArrayList;
import java.util.List;

import projectrts.model.core.Position;

/**
 * A node with the primary purpose of helping with pathfinding.
 * @author Bjorn Persson Mattsson
 *
 */
public class Node {

	private Position position;
	private boolean occupied = false;
	private int distanceToObstacle = 10;
	private float cost = 1;
	private List<Node> neighbours = new ArrayList<Node>();
	
	/**
	 * Creates a new node at the position.
	 * @param position Position.
	 */
	public Node(Position position)
	{
		this(position.getX(), position.getY());
	}
	/**
	 * Creates a new node at the position of the x and y coordinates.
	 * @param posX X coordinate.
	 * @param posY Y coordinate.
	 */
	public Node(double posX, double posY)
	{
		this.position = new Position(posX, posY);
	}
	
	/**
	 * @return Position of the node.
	 */
	public Position getPosition()
	{
		return this.position.clone();
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	/**
	 * Updates the distance to the nearest obstacle.
	 */
	public void updateDistanceToObstaccle()
	{
		// TODO Implement this algorithm
	}
	
	/**
	 * Returns a list of all nodes that are neighbours (connected) to this node.
	 * @return List of the node's neighbours.
	 */
	public List<Node> getNeighbours()
	{
		// May need to "secure" this list
		return neighbours;
	}
	
	/**
	 * Adds a new neighbour (connection) to this node.
	 * @param node The new neighbour.
	 */
	public void addNeighbour(Node node)
	{
		if (!this.equals(node))
		{
			neighbours.add(node);
		}
	}

	/*
	 * Auto generated hashcode method based on position.
	 */
	@Override
	public int hashCode() {
		final int prime = 173;
		int result = 1;
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	/*
	 * Auto generated equals method based on Position.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
	
	public float getCost() {
		return cost;
	}
	public void setCost(float cost)
	{
		this.cost = cost;
	}
}