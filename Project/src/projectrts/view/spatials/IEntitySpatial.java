package projectrts.view.spatials;

import projectrts.model.entities.IEntity;

import com.jme3.scene.shape.Box;
/**
 * 
 * @author Bjorn Persson Mattsson
 *
 */
public interface IEntitySpatial {

	// TODO Markus: Add javadoc
	public AbstractSpatial createSpatial(String name, Box box, IEntity entity);
}
