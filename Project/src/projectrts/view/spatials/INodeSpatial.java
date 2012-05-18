package projectrts.view.spatials;

import projectrts.model.world.INode;

import com.jme3.scene.shape.Box;

/**
 * 
 * @author Bjorn Persson Mattsson
 *
 */
interface INodeSpatial {

	/**
	 * Creates and returns a new NodeSpatial using the parameters provided.
	 * @param name Name
	 * @param box Box
	 * @param node Node
	 * @return The new NodeSpatial.
	 */
	public AbstractSpatial createSpatial(String name, Box box, INode node);
}
