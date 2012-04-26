package projectrts.view.spatials;

import projectrts.global.utils.MaterialManager;
import projectrts.model.pathfinding.INode;
import projectrts.view.controls.ControlFactory;
import projectrts.view.controls.NodeControl;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.shape.Box;

/**
 * 
 * @author Bjorn Persson Mattsson
 *
 */
//TODO Jakob: PMD error, "A class which only has private constructors should be final"
public class DebugNodeSpatial extends AbstractSpatial implements INodeSpatial {
private Material material;
	
	static {
		SpatialFactory.INSTANCE.registerSpatial(DebugNodeSpatial.class.getSimpleName(), new DebugNodeSpatial("DebugNodeSpatialCreator", new Box()));
	}
	
	private DebugNodeSpatial(String name, Box box) {
		super(name, box);
	}

	@Override
	public AbstractSpatial createSpatial(String name, Box box, INode node) {
		material = MaterialManager.INSTANCE.getMaterial("Unshaded");
		material.setColor("Color", ColorRGBA.Green);
		
		DebugNodeSpatial newSpatial = new DebugNodeSpatial(name, box);
		newSpatial.setMaterial(material);
		newSpatial.addControl(ControlFactory.INSTANCE.createNodeControl(NodeControl.class.getSimpleName(), node));
		return newSpatial;
	}

}
