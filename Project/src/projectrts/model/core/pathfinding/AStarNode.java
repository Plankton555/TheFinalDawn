package projectrts.model.core.pathfinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import projectrts.model.core.Position;

/**
 * A node that is specifically configured to work with the A* pathfinding algorithm.
 * @author Bjorn Persson Mattsson
 *
 */
public class AStarNode implements Comparable<AStarNode> {
	
	private Node node;
	private int totalCost = 0;
	private int costFromStart;
	private int heuristic;
	private AStarNode parent;
	
	/**
	 * Creates a new AStarNode that refers to the provided node.
	 * @param node Node
	 */
	public AStarNode(Node node)
	{
		this.node = node;
	}
	
	/**
	 * @return The neighbours (adjacent nodes) to this one.
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
	 * @param parentNode Parent node
	 * @param endNode End node (used to calculate heuristic)
	 */
	public void calculateCost(AStarNode parentNode, AStarNode endNode)
	{
		Position mePos = this.getPosition();
		Position parPos = parentNode.getPosition();
		Position endPos = endNode.getPosition();
		int distance = calcNodeDistance(mePos, parPos);
		
		this.costFromStart = (int) Math.round(parentNode.getCostFromStart() + distance*node.getCost());
		this.heuristic = ((int) (Math.abs(endPos.getX() - mePos.getX()) + Math.abs(endPos.getY() - mePos.getY())))*10;
		this.totalCost = this.costFromStart + this.heuristic;
		this.parent = parentNode;
	}

	/**
	 * Refreshes the cost from start for this node.
	 * @param parentNode Parent node
	 */
	public void refreshCost(AStarNode parentNode)
	{
		Position mePos = this.getPosition();
		Position parPos = parentNode.getPosition();
		int distance = calcNodeDistance(mePos, parPos);
		
		int newCostFromStart = (int) Math.round(parentNode.getCostFromStart() + distance*node.getCost());
		if (newCostFromStart < this.costFromStart)
		{
			this.costFromStart = newCostFromStart;
			this.totalCost = this.costFromStart + this.heuristic;
			this.parent = parentNode;
		}
	}
	
	// Calculates "A*" distance between two adjacent nodes.
	private int calcNodeDistance(Position pos1, Position pos2) {
		int distance = 0;
		if (pos1.getX() == pos2.getX() && pos1.getY() == pos2.getY())
		{
			distance = 0;
		}
		else if (pos1.getX() != pos2.getX() && pos1.getY() != pos2.getY())
		{
			distance = 14; //diagonal: sqrt(2) ~= 1.4
		}
		else
		{
			distance = 10;
		}
		return distance;
	}

	/**
	 * @return The total cost from the start node.
	 */
	public int getCostFromStart()
	{
		return costFromStart;
	}
	
	/**
	 * @return The total cost of the node (cost from start + heuristic).
	 */
	public int getTotalCost()
	{
		return totalCost;
	}
	
	/**
	 * @return The heuristic of the node.
	 */
	public int getHeuristic()
	{
		return heuristic;
	}
	
	/**
	 * @return Position of the node.
	 */
	public Position getPosition()
	{
		return node.getPosition();
	}
	
	/**
	 * @return Parent node
	 */
	public AStarNode getParent()
	{
		return parent;
	}
	
	public static Comparator<AStarNode> getHeuristicComparator()
	{
		return new Comparator<AStarNode>() {

			@Override
			public int compare(AStarNode o1, AStarNode o2) {
				return Integer.compare(o1.getHeuristic(), o2.getHeuristic());
			}
		};
	}
	
	@Override
	public int compareTo(AStarNode other) {
		return Integer.compare(getTotalCost(), other.getTotalCost());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AStarNode other = (AStarNode) obj;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		return true;
	}

	/**
	 * @return true if this node is unwalkable, otherwise false.
	 */
	public boolean isObstacle() {
		return node.isOccupied();
	}
}
