package projectrts.view.spatials;

import projectrts.model.core.entities.IEntity;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
/**
 * 
 * @author Markus Ekstr�m
 *
 */
public abstract class AbstractSpatial extends Geometry{
	
	protected AbstractSpatial(String name, Box box) {
		super(name, box);
	}
	
	public abstract AbstractSpatial createSpatial(String name, Material material, Box box, IEntity entity);
}