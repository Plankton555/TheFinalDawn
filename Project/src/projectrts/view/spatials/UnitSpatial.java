package projectrts.view.spatials;

import projectrts.model.core.entities.IEntity;
import projectrts.view.controls.ControlFactory;
import projectrts.view.controls.MoveControl;

import com.jme3.material.Material;
import com.jme3.scene.shape.Box;
/**
 * 
 * @author Markus Ekström
 *
 */
public class UnitSpatial extends AbstractSpatial {
	
	static {
		SpatialFactory.INSTANCE.registerSpatial(UnitSpatial.class.getSimpleName(), new UnitSpatial("UnitSpatialCreator", new Box()));
	}
	
	private UnitSpatial(String name, Box box) {
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
	public AbstractSpatial createSpatial(String name, Material material, Box box, IEntity entity) {
		UnitSpatial newSpatial = new UnitSpatial(name, box);
		newSpatial.setMaterial(material);
		newSpatial.addControl(ControlFactory.INSTANCE.createControl(MoveControl.class.getSimpleName(), entity));
		
		return newSpatial;
	}
	
}
