package projectrts.view.spatials;

import projectrts.model.entities.IEntity;

import com.jme3.scene.shape.Box;

/**
 * An interface for entity spatials.
 * 
 * @author Markus Ekström
 * 
 */
interface IEntitySpatial {

	/**
	 * Creates a spatial of the same class.
	 * 
	 * @param name
	 *            The desired name of the new spatial.
	 * @param box
	 *            The desired shape of the new spatial.
	 * @param entity
	 *            The entity the spatial represents.
	 * @return A spatial representing the entity.
	 */
	AbstractSpatial createSpatial(String name, Box box, IEntity entity);
}