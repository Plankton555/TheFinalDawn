package projectrts.model.core.entities;

import projectrts.model.core.IPlayer;

/**
 * 
 * @author Jakob Svensson
 *
 */
public interface IPlayerControlledEntity extends IEntity{
	/**
	 * @return The owner of the entity.
	 */
	public IPlayer getOwner();
	
}
