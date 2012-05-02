package projectrts.view.controls;

import projectrts.model.entities.IEntity;

import com.jme3.scene.control.AbstractControl;
/**
 * An interface for the custom controls.
 * @author Markus Ekström
 *
 */
public interface IEntityControl {
	
	/**
	 * Creates a control of the same class and returns it as an AbstractControl.
	 * @param entity The entity the control should control.
	 * @return A new instance of the control.
	 */
	public AbstractControl createControl(IEntity entity);
}
