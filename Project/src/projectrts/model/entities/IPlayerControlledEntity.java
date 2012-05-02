package projectrts.model.entities;

import java.util.List;

import projectrts.model.abilities.IAbility;
import projectrts.model.entities.PlayerControlledEntity.State;
import projectrts.model.player.IPlayer;
import projectrts.model.utils.Position;

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
	 * @return The health of the entity.
	 */
	public int getCurrentHealth();
	
	/**
	 * @return The maximum health of the entity.
	 */
	public int getMaxHealth();
	
	/**
	 * @return The damage of the entity.
	 */
	public int getDamage();

	/**
	 * @return The state of the unit;
	 */
	public State getState();
	
	// TODO Jakob: Add javadoc
	public void dealDamageTo(int damage);
}
