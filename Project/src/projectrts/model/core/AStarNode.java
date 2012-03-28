package projectrts.model.core;

import java.util.ArrayList;
import java.util.List;

public class AStarNode implements Comparable<AStarNode> {
	
	private Node node;
	private float cost = 0;
	
	/**
	 * Creates a new AStarNode that refers to the provided node.
	 * @param node Node
	 */
	public AStarNode(Node node)
	{
		this.node = node;
	}
	
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
	 * Sets the cost for this node. AStarNodes are sorted by cost.
	 * @param cost Cost
	 */
	public void setCost(float cost)
	{
		this.cost = cost;
	}
	
	@Override
	public int compareTo(AStarNode other) {
		// Need to negate since the lower the cost, the higher the node should be sorted.
		return -Float.compare(cost, other.cost);
	}
	
	/**
	 * @param other Another AStarNode
	 * @return true if both AStarNodes refer to the same Node, otherwise false.
	 */
	public boolean sameNodeAs(AStarNode other)
	{
		return node.equals(other.node);
	}
}
