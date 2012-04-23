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
 * @author Bjorn Persson Mattsson
 *
 */
public class DebugNodeSpatial extends AbstractSpatial implements ISpatial {
private Material material;
	
	static {
		SpatialFactory.INSTANCE.registerSpatial(DebugNodeSpatial.class.getSimpleName(), new DebugNodeSpatial("DebugNodeSpatialCreator", new Box()));
	}
	
	private DebugNodeSpatial(String name, Box box) {
		super(name, box);
	}

	@Override
	public AbstractSpatial createSpatial(String name, Box box) {
		material = MaterialManager.INSTANCE.getMaterial("Unshaded");
		material.setColor("Color", ColorRGBA.Green);
		
		DebugNodeSpatial newSpatial = new DebugNodeSpatial(name, box);
		newSpatial.setMaterial(material);
		return newSpatial;
	}

}
