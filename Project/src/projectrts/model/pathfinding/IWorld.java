package projectrts.model.pathfinding;

import java.util.List;

import projectrts.model.utils.Position;

/**
 * 
 * @author Bjorn P Mattsson
 *
 */
public interface IWorld {

	// TODO Plankton: !Add javadoc
	public int getWorldWidth();
	public int getWorldHeight();
	
	public INode[][] getNodes();
	public INode getNodeAt(Position p);
	public void setNodesOccupied(INode nodeInCenter, float entitySize, int entityID);
	public List<INode> getNodesAt(Position centerPos, float size);
}
