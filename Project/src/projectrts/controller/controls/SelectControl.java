package projectrts.controller.controls;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import projectrts.global.utils.Utils;
import projectrts.model.core.Position;
import projectrts.model.core.entities.IEntity;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

public class SelectControl extends AbstractControl{
	private IEntity entity;
	
	public SelectControl(IEntity entity) {
		super();
		this.entity = entity;
	}
	
	@Override
	public Control cloneForSpatial(Spatial spatial) {
		SelectControl control = new SelectControl(entity);
		control.setSpatial(spatial);
		return control;
	}

	@Override
	protected void controlRender(RenderManager arg0, ViewPort arg1) {
		
	}

	@Override
	protected void controlUpdate(float arg0) {
		if(this.enabled && spatial != null) {
			Position pos = entity.getPosition();
			Vector3f worldPos = Utils.INSTANCE.convertModelToWorld(pos);
			Vector3f moveVector = worldPos.subtract(spatial.getWorldTranslation());
			if(!moveVector.equals(Vector3f.ZERO)) {
				spatial.move(moveVector);
			}		
		}
	}
}
