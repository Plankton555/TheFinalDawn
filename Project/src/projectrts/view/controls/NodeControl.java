package projectrts.view.controls;

import projectrts.io.MaterialManager;
import projectrts.model.world.INode;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

/**
 * A custom control that handles the debug nodes color change when they are occupied.
 * @author Bjorn Persson Mattsson
 *
 */
public final class NodeControl extends AbstractControl implements INodeControl {

	private INode node;
	
	static {
		ControlFactory.INSTANCE.registerControl("NodeControl", new NodeControl(null));
	}
	
	private NodeControl(INode node) {
		super();
		this.node = node;
	}
	
	@Override
	public Control cloneForSpatial(Spatial spatial) {
		NodeControl control = new NodeControl(node);
		control.setSpatial(spatial);
		return control;
	}

	@Override
	public AbstractControl createControl(INode node) {
		return new NodeControl(node);
	}

	@Override
	protected void controlRender(RenderManager arg0, ViewPort arg1) {
	}

	/**
	 * Automatically hooks into the update loop. Should not be manually called!
	 * @param tpf Time-per-frame
	 */
	@Override
	protected void controlUpdate(float arg0) {
		if(this.enabled && spatial != null)
		{
			Material material = MaterialManager.INSTANCE.getMaterial("Unshaded");
			if (node.isOccupied())
			{
				material.setColor("Color", ColorRGBA.Red);
			}
			else
			{
				material.setColor("Color", ColorRGBA.Green);
			}
			spatial.setMaterial(material);
		}
	}

}
