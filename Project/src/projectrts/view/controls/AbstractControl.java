package projectrts.view.controls;

import projectrts.model.core.entities.IEntity;
/**
 * 
 * @author Markus Ekstr�m
 *
 */
public abstract class AbstractControl extends com.jme3.scene.control.AbstractControl{
	
	public abstract AbstractControl createControl(IEntity entity);

}
