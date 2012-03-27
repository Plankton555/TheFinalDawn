package projectrts.model.core;

import java.util.ArrayList;
import java.util.List;

/**
 * A node with the primary purpose of helping with pathfinding.
 * @author Bjorn Persson Mattsson
 *
 */
public class Node {

	private Position position;
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
	public Node(float posX, float posY)
	{
		this.position = new Position(posX, posY);
	}
	
	/**
	 * Returns a list of all nodes that are neighbours (connected) to this node.
	 * @return List of the node's neighbours.
	 */
	public List<Node> getNeighbours()
	{
		return neighbours;
	}
	
	/**
	 * @return Position of the node.
	 */
	public Position getPosition()
	{
		return this.position.clone();
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
}
