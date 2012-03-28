package projectrts.model.core;

import java.util.ArrayList;
import java.util.List;

import projectrts.model.core.utils.ModelUtils;

/**
 * A node that is specifically configured to work with the A* pathfinding algorithm.
 * @author Bjorn Persson Mattsson
 *
 */
public class AStarNode implements Comparable<AStarNode> {
	
	private Node node;
	private float totalCost = 0;
	private float costFromStart;
	private float heuristic;
	
	/**
	 * Creates a new AStarNode that refers to the provided node.
	 * @param node Node
	 */
	public AStarNode(Node node)
	{
		this.node = node;
	}
	
	/**
	 * @return The neighbours (adjecent nodes) to this one.
	 */
	public List<AStarNode> getNeighbours()
	{
		List<AStarNode> output = new ArrayList<AStarNode>();
		List<Node> neighbours = node.getNeighbours();
		
		for (Node n : neighbours)
		{
			output.add(new AStarNode(n));
		}
		return output;
	}
	
	/**
	 * Calculated the cost for this node.
	 * @param cost Cost
	 */
	public void calculateCost(AStarNode parentNode, AStarNode endNode)
	{
		this.costFromStart = parentNode.getCostFromStart() + node.getCost();
		this.heuristic = ModelUtils.INSTANCE.getDistance(this.getPosition(), endNode.getPosition());
		this.totalCost = this.costFromStart + this.heuristic;
	}
	
	/**
	 * @return The total cost from the start node.
	 */
	public float getCostFromStart()
	{
		return costFromStart;
	}
	
	/**
	 * @return The total cost of the node (cost from start + heuristic).
	 */
	public float getTotalCost()
	{
		return totalCost;
	}
	
	/**
	 * @return Position of the node.
	 */
	public Position getPosition()
	{
		return node.getPosition();
	}
	
	@Override
	public int compareTo(AStarNode other) {
		// Need to negate since the lower the cost, the higher the node should be sorted.
		return -Float.compare(getTotalCost(), other.getTotalCost());
	}
	
	/**
	 * @param other Another AStarNode
	 * @return true if both AStarNodes refer to the same Node, otherwise false.
	 */
	public boolean sameNodeAs(AStarNode other)
	{
		return node.equals(other.node);
	}

	/**
	 * @return true if this node is unwalkable, otherwise false.
	 */
	public boolean isObstacle() {
		return node.isOccupied();
	}
}
