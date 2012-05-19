package projectrts.model.entities;

import projectrts.model.world.Position;


/**
 * 
 * @author Filip Brynfors
 *
 */
public interface IEntity{
	
	/**
	 * @return The position of the entity.
	 */
	Position getPosition();
	
	/**
	 * @return The size of the entity.
	 */
	float getSize();
	
	/**
	 * @return The max speed of the entity.
	 */
	float getSpeed();
	
	/**
	 * @return The entity's ID
	 */
	int getEntityID();
	
	/**
	 * @return The name of the entity
	 */
	String getName();
}