package projectrts.view.spatials;

import projectrts.global.utils.MaterialManager;
import projectrts.model.entities.IEntity;
import projectrts.view.controls.ControlFactory;
import projectrts.view.controls.SelectControl;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.shape.Box;

/**
 * A spatial for the selection effects.
 * @author Markus Ekström
 *
 */
public class SelectSpatial extends AbstractSpatial implements IEntitySpatial{
	private Material material;
	
	static {
		SpatialFactory.INSTANCE.registerSpatial("SelectSpatial", new SelectSpatial("SelectSpatialCreator", new Box()));
	}
	
	private SelectSpatial(String name, Box box) {
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
		material.setColor("Color", ColorRGBA.Green);
		
		SelectSpatial newSpatial = new SelectSpatial(name, box);
		newSpatial.setMaterial(material);
		newSpatial.addControl(ControlFactory.INSTANCE.createEntityControl(SelectControl.class.getSimpleName(), entity));
		
		return newSpatial;
	}
}
