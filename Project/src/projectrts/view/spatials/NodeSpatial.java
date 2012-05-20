package projectrts.view.spatials;

import projectrts.io.MaterialManager;
import projectrts.model.world.INode;
import projectrts.view.controls.ControlFactory;
import projectrts.view.controls.NodeControl;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.shape.Box;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public final class NodeSpatial extends AbstractSpatial implements INodeSpatial {
	private INode node;

	static {
		SpatialFactory.registerSpatial(NodeSpatial.class.getSimpleName(),
				new NodeSpatial(NodeSpatial.class.getSimpleName() + "Creator",
						new Box()));
	}

	private NodeSpatial(String name, Box box) {
		super(name, box);
	}

	@Override
	public AbstractSpatial createSpatial(String name, Box box, INode node) {
		material = MaterialManager.getMaterial("Unshaded");
		material.setColor("Color", ColorRGBA.Green);
		NodeSpatial newSpatial = new NodeSpatial(name, box);
		newSpatial.node = node;
		newSpatial.setMaterial(material);
		newSpatial.addControl(ControlFactory.createNodeControl(
				NodeControl.class.getSimpleName(), node));
		return newSpatial;
	}

	public INode getNode() {
		return this.node;
	}
}