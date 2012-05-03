package projectrts.view.controls;

import projectrts.controller.InGameState;
import projectrts.model.world.INode;
import projectrts.model.world.Position;

import com.jme3.math.Vector3f;
import com.jme3.scene.control.AbstractControl;
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
