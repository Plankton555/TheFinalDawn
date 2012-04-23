package projectrts.view.spatials;

import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
/**
 * 
 * @author Markus Ekström
 *
 */
public abstract class AbstractSpatial extends Geometry{
	
	protected AbstractSpatial(String name, Box box) {
		super(name, box);
	}
}