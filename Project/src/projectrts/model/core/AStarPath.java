package projectrts.model.core;

import java.util.Stack;

public class AStarPath {
	
	private Stack<AStarNode> nodeStack = new Stack<AStarNode>();
	
	public void addNodeToPath(AStarNode node)
	{
		nodeStack.push(node);
	}
	
	public Position getNextNodePosition()
	{
		return nodeStack.peek().getPosition();
	}
	
	public void removeNodeFromPath()
	{
		nodeStack.pop();
	}
	
	public int nrOfNodesLeft()
	{
		return nodeStack.size();
	}
}
