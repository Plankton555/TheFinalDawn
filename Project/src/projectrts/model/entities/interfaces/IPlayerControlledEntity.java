package projectrts.model.entities.interfaces;

import java.util.List;

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
	 * @return The list of available abilities of the entity.
	 */
	public List<IAbility> getAbilities();
	
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
	
	/**
	 * Uses the given ability if it has it, otherwise nothing happens.
	 * @param ability The ability to use.
	 * @param pos The position the ability is used at if applicable.
	 */
	public void doAbility(String ability, Position pos);
	
	// TODO Jakob: Add javadoc
	public void dealDamageTo(int damage);
}
