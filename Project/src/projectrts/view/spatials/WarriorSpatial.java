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
 * @author Markus Ekstr�m
 *
 */
public class WarriorSpatial extends AbstractSpatial {
	private Material material;
	
	static {
		SpatialFactory.INSTANCE.registerSpatial(WarriorSpatial.class.getSimpleName(), new WarriorSpatial("UnitSpatialCreator", new Box()));
	}
	
	private WarriorSpatial(String name, Box box) {
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
		material.setColor("Color", ColorRGBA.Pink);
		
		WarriorSpatial newSpatial = new WarriorSpatial(name, box);
		newSpatial.setMaterial(material);
		newSpatial.addControl(ControlFactory.INSTANCE.createControl(MoveControl.class.getSimpleName(), entity));
		return newSpatial;
	}
}