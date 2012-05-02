package projectrts.model.entities;

import projectrts.model.utils.Position;

/**
 * 
 * @author Filip Brynfors
 *
 */
public interface IEntity{
	
	/**
	 * @return The position of the entity.
	 */
	public Position getPosition();
	
	/**
	 * @return The size of the entity.
	 */
	public float getSize();
	
	/**
	 * @return The max speed of the entity.
	 */
	public float getSpeed();
	
	/**
	 * @return The entity's ID
	 */
	public int getEntityID();
	
	/**
	 * @return The name of the entity
	 */
	public String getName();
}