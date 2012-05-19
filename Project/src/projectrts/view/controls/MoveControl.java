package projectrts.view.controls;

import projectrts.model.entities.IEntity;
import projectrts.model.world.Position;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

/**
 * A custom Control that handles entity-spatial's movement.
 * 
 * @author Markus Ekström
 * 
 */
public final class MoveControl extends AbstractCustomControl implements
		IEntityControl {
	// TODO Markus: PMD: Private field 'entity' could be made final; it is only initialized in the declaration or constructor.
	private IEntity entity;

	static {
		ControlFactory.INSTANCE.registerControl("MoveControl", new MoveControl(
				null));
	}

	private MoveControl(IEntity entity) {
		super();
		this.entity = entity;
	}

	@Override
	public Control cloneForSpatial(Spatial spatial) {
		MoveControl control = new MoveControl(entity);
		control.setSpatial(spatial);
		return control;
	}

	@Override
	protected void controlRender(RenderManager arg0, ViewPort arg1) {

	}

	/**
	 * Automatically hooks into the update loop. Should not be manually called!
	 * 
	 * @param tpf
	 *            Time-per-frame
	 */
	@Override
	protected void controlUpdate(float tpf) {
		if (this.enabled && spatial != null) {
			Position pos = entity.getPosition(); // Gets the position from it's associated entity.
			Vector3f worldPos = this.convertModelToWorld(pos); // Converts it to world position.
			Vector3f moveVector = worldPos.subtract(spatial
					.getWorldTranslation()); // Subtracts the current position from the desired to get a movement vector.
			if (!moveVector.equals(Vector3f.ZERO)) { // If the spatial needs to be moved.
				spatial.move(moveVector); // Move the spatial according to the movement vector.
			}
		}
	}

	@Override
	public AbstractControl createControl(IEntity entity) {
		return new MoveControl(entity);
	}

	// TODO Markus: Add javadoc
	public IEntity getEntity() {
		return entity;
	}
}