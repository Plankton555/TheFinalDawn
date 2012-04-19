package projectrts.view.spatials;

import projectrts.model.entities.IEntity;

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
	
	// TODO Markus: Add javadoc
	public abstract AbstractSpatial createSpatial(String name, Box box, IEntity entity);
}