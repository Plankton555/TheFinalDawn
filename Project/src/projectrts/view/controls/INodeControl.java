package projectrts.view.controls;

import com.jme3.scene.control.AbstractControl;

import projectrts.model.pathfinding.INode;
/**
 * 
 * @author Bjorn Persson Mattsson
 *
 */
public interface INodeControl {

	// TODO Plankton: Add javadoc
	public AbstractControl createControl(INode node);
}
