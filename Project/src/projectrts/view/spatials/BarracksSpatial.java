package projectrts.view.spatials;

import projectrts.global.utils.MaterialManager;
import projectrts.model.entities.IEntity;
import projectrts.view.controls.ControlFactory;
import projectrts.view.controls.MoveControl;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.shape.Box;

/**
 * 
 * @author Jakob Svensson
 *
 */
public class BarracksSpatial extends AbstractSpatial implements IEntitySpatial{
	private Material material;
	
	static {
		SpatialFactory.INSTANCE.registerSpatial(BarracksSpatial.class.getSimpleName(), new BarracksSpatial("HeadquarterSpatialCreator", new Box()));
	}
	
	private BarracksSpatial(String name, Box box) {
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
		material.setColor("Color", ColorRGBA.Black);
		
		BarracksSpatial newSpatial = new BarracksSpatial(name, box);
		newSpatial.setMaterial(material);
		newSpatial.addControl(ControlFactory.INSTANCE.createEntityControl(MoveControl.class.getSimpleName(), entity));
		return newSpatial;
	}

}