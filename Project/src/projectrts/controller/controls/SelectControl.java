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

public class SelectControl extends AbstractControl implements PropertyChangeListener{
	private IEntity entity;
	
	public SelectControl(IEntity entity, Spatial spatial) {
		super();
		this.entity = entity;
		this.entity.addListener(this);
		this.setSpatial(spatial);
		spatial.addControl(this);
	}
	
	@Override
	public Control cloneForSpatial(Spatial spatial) {
		SelectControl control = new SelectControl(entity, spatial);
		return control;
	}
	
	public Control cloneForSpatial(Spatial spatial, IEntity entity) {
		SelectControl control = new SelectControl(entity, spatial);
		return control;
	}

	@Override
	protected void controlRender(RenderManager arg0, ViewPort arg1) {
		
	}

	@Override
	protected void controlUpdate(float arg0) {
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent pce) {
		if(pce.getPropertyName() == "move" && pce.getNewValue() instanceof Position) {
			Position pos = (Position)pce.getNewValue();
			Vector3f worldPos = spatial.getWorldTranslation().add(Utils.INSTANCE.convertModelToWorld(pos));
			spatial.setLocalTranslation(worldPos);
		}
	}

}
