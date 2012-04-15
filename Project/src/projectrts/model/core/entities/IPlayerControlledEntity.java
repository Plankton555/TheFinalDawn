package projectrts.model.core.entities;

import java.util.List;

import projectrts.model.core.IPlayer;
import projectrts.model.core.abilities.IAbility;

/**
 * 
 * @author Jakob Svensson, Modified by Filip Brynfors
 *
 */
public interface IPlayerControlledEntity extends IEntity{
	/**
	 * @return The owner of the entity.
	 */
	public IPlayer getOwner();
	
	/**
	 * @return The sight range of the entity.
	 */
	public float getSightRange();
	
	/**
	 * @return The list of available abilities of the entity
	 */
	public List<IAbility> getAbilities();
	
	/**
	 * @return The health of the entity
	 */
	public int getCurrentHealth();
	
	/**
	 * @return The maximum health of the entity
	 */
	public int getMaxHealth();
}
