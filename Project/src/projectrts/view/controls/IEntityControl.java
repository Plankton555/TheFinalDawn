package projectrts.view.controls;

import com.jme3.scene.control.AbstractControl;

import projectrts.model.entities.IEntity;
/**
 * 
 * @author Markus Ekström
 *
 */
// TODO Markus: Class names shouldn't shadow simple name of superclass
public interface IEntityControl {
	
	// TODO Markus: Add javadoc
	public AbstractControl createControl(IEntity entity);
}
