
package projectrts.view.controls;

import projectrts.model.entities.IEntity;
import projectrts.model.world.Position;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
//TODO Markus: Check if MoveControl could be used instead of this class.
/**
 * A custom Control that handles selected-spatial's movement.
 * Currently is no different from MoveControl and is probably redundant.
 * 
 * @author Markus Ekström
 *
 */
public final class SelectControl extends AbstractCustomControl implements IEntityControl{
	private IEntity entity;
	
	static {
		ControlFactory.INSTANCE.registerControl("SelectControl", new SelectControl(null));
	}
	
	private SelectControl(IEntity entity) {
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

	/**
	 * Automatically hooks into the update loop. Should not be manually called!
	 * @param tpf Time-per-frame
	 */
	@Override
	protected void controlUpdate(float arg0) {
		if(this.enabled && spatial != null) {
			Position pos = entity.getPosition(); // Gets the position from it's associated entity. 
			Vector3f worldPos = this.convertModelToWorld(pos); // Converts it to world position.
			Vector3f moveVector = worldPos.subtract(spatial.getWorldTranslation()); // Subtracts the current position from the desired to get a movement vector.
			if(!moveVector.equals(Vector3f.ZERO)) { // If the spatial needs to be moved.
				spatial.move(moveVector); // Move the spatial according to the movement vector.
			}		
		}
	}

	@Override
	public AbstractControl createControl(IEntity entity) {
		return new SelectControl(entity);
	}
}
