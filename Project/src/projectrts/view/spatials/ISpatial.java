package projectrts.view.spatials;

import projectrts.model.pathfinding.INode;

import com.jme3.scene.shape.Box;

/**
 * 
 * @author Bjorn Persson Mattsson
 *
 */
public interface ISpatial {

	// TODO Plankton: Add javadoc
	public AbstractSpatial createSpatial(String name, Box box, INode node);
}
