package projectrts.model.core.entities;

import projectrts.model.core.Position;

/**
 * 
 * @author Filip Brynfors
 *
 */
public interface IEntity {
	/**
	 * @return The position of the entity.
	 */
	public Position getPosition();
	
	/**
	 * @return The size of the entity.
	 */
	public float getSize();
	
	
	/**
	 * @return The name of the entity
	 */
	public String getName();
}