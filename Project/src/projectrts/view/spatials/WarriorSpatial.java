package projectrts.view.spatials;

import projectrts.io.MaterialManager;
import projectrts.io.TextureManager;
import projectrts.model.entities.AbstractPlayerControlledEntity;
import projectrts.model.entities.IEntity;
import projectrts.model.entities.PlayerColor;
import projectrts.view.controls.ControlFactory;
import projectrts.view.controls.MoveControl;

import com.jme3.material.RenderState.BlendMode;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

/**
 * 
 * @author Markus Ekstr�m
 * 
 */
public final class WarriorSpatial extends AbstractSpatial implements
		IEntitySpatial {

	static {
		SpatialFactory.registerSpatial(WarriorSpatial.class.getSimpleName(),
				new WarriorSpatial(WarriorSpatial.class.getSimpleName()
						+ "Creator", new Box()));
	}

	private WarriorSpatial(String name, Box box) {
		super(name, box);
	}

	/**
	 * Creates and returns a new spatial using the parameters provided.
	 * 
	 * @param name
	 *            The name of the new spatial.
	 * @param material
	 *            The material of the new spatial.
	 * @param box
	 *            The shape of the new spatial.
	 * @param controlList
	 *            A list of the controls the spatial will use.
	 */
	@Override
	public AbstractSpatial createSpatial(String name, Box box, IEntity entity) {
		material = MaterialManager.getMaterial("Unshaded");
		// material.setColor("Color", ColorRGBA.Pink);
		Texture texture = null;
		if (entity instanceof AbstractPlayerControlledEntity) {
			AbstractPlayerControlledEntity pce = (AbstractPlayerControlledEntity) entity;
			if (pce.getOwner().getColor() == PlayerColor.BLUE) {
				texture = TextureManager.getTexture("Drone_blue");
			}
			else if (pce.getOwner().getColor() == PlayerColor.RED) {
				texture = TextureManager.getTexture("Drone_red");
			}
		}
		material.setTexture("ColorMap", texture);
		material.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);

		WarriorSpatial newSpatial = new WarriorSpatial(name, box);
		newSpatial.setMaterial(material);
		newSpatial.setQueueBucket(Bucket.Transparent);
		newSpatial.addControl(ControlFactory.createEntityControl(
				MoveControl.class.getSimpleName(), entity));
		return newSpatial;
	}
}