package projectrts.view.spatials;

import projectrts.io.MaterialManager;
import projectrts.io.TextureManager;
import projectrts.model.entities.IEntity;
import projectrts.view.controls.ControlFactory;
import projectrts.view.controls.MoveControl;

import com.jme3.material.RenderState.BlendMode;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
/**
 * 
 * @author Jakob Svensson
 *
 */
public final class RangedSpatial extends AbstractSpatial implements IEntitySpatial {
	
	static {
		SpatialFactory.registerSpatial(RangedSpatial.class.getSimpleName(), new RangedSpatial("UnitSpatialCreator", new Box()));
	}
	
	private RangedSpatial(String name, Box box) {
		super(name, box);
	}
	
	/**
	 * Creates and returns a new spatial using the parameters provided.
	 * @param name The name of the new spatial.
	 * @param material The material of the new spatial.
	 * @param box The shape of the new spatial.
	 * @param controlList A list of the controls the spatial will use.
	 */
	@Override
	public AbstractSpatial createSpatial(String name, Box box, IEntity entity) {
		material = MaterialManager.INSTANCE.getMaterial("Unshaded");
		//material.setColor("Color", ColorRGBA.Magenta);
		Texture texture = TextureManager.INSTANCE.getTexture("Marine");
		material.setTexture("ColorMap", texture);
		material.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		
		RangedSpatial newSpatial = new RangedSpatial(name, box);
		newSpatial.setMaterial(material);
		newSpatial.setQueueBucket(Bucket.Transparent);
		newSpatial.addControl(ControlFactory.INSTANCE.createEntityControl(MoveControl.class.getSimpleName(), entity));
		return newSpatial;
	}
}
