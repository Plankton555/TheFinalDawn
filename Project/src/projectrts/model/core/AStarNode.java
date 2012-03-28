package projectrts.model.core;

public class AStarNode extends Node implements Comparable<AStarNode> {
	
	private float cost = 0;
	public AStarNode(Node node)
	{
		super(node.getPosition());
	}
	
	public void setCost(float cost)
	{
		this.cost = cost;
	}
	
	@Override
	public int compareTo(AStarNode other) {
		// Need to negate since the lower the cost, the higher the node should be sorted.
		return -Float.compare(cost, other.cost);
	}
}
