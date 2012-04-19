package projectrts.view.controls;

import projectrts.model.entities.IEntity;
/**
 * 
 * @author Markus Ekström
 *
 */
// TODO Markus: Class names shouldn't shadow simple name of superclass
public abstract class AbstractControl extends com.jme3.scene.control.AbstractControl{
	
	// TODO Markus: Add javadoc
	public abstract AbstractControl createControl(IEntity entity);
}
