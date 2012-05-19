package projectrts.view.controls;

import projectrts.model.world.INode;

import com.jme3.scene.control.AbstractControl;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
interface INodeControl {

	/**
	 * Creates a NodeControl for the provided node.
	 * 
	 * @param node
	 *            Node.
	 * @return AbstractControl that is the NodeControl.
	 */
	AbstractControl createControl(INode node);
}
