package projectrts.view.spatials;

import projectrts.io.MaterialManager;
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
//TODO Anyone: PMD: Class cannot be instantiated and does not provide any static methods or fields
public final class HeadquarterSpatial extends AbstractSpatial implements IEntitySpatial{
	// TODO Jakob: PMD: Perhaps 'material' could be replaced by a local variable.
	private Material material;
	
	static {
		SpatialFactory.INSTANCE.registerSpatial(HeadquarterSpatial.class.getSimpleName(), new HeadquarterSpatial("HeadquarterSpatialCreator", new Box()));
	}
	
	private HeadquarterSpatial(String name, Box box) {
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
		material.setColor("Color", ColorRGBA.Gray);
		
		HeadquarterSpatial newSpatial = new HeadquarterSpatial(name, box);
		newSpatial.setMaterial(material);
		newSpatial.addControl(ControlFactory.INSTANCE.createEntityControl(MoveControl.class.getSimpleName(), entity));
		return newSpatial;
	}

}
