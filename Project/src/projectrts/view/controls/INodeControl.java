package projectrts.view.controls;

import com.jme3.scene.control.AbstractControl;

import projectrts.model.pathfinding.INode;
/**
 * 
 * @author Bjorn Persson Mattsson
 *
 */
public interface INodeControl {

	/**
	 * Creates a NodeControl for the provided node.
	 * @param node Node.
	 * @return AbstractControl that is the NodeControl.
	 */
	public AbstractControl createControl(INode node);
}
