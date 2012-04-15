package projectrts.controller.controls;

import projectrts.model.core.entities.IEntity;

public abstract class AbstractControl extends com.jme3.scene.control.AbstractControl {
	
	public abstract AbstractControl createControl(IEntity entity);

}
