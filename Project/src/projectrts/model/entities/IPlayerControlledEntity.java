package projectrts.model.entities;

/**
 * 
 * @author Jakob Svensson, Modified by Filip Brynfors
 * 
 */
public interface IPlayerControlledEntity extends IEntity {
	/**
	 * @return The owner of the entity.
	 */
	IPlayer getOwner();

	/**
	 * @return The sight range of the entity.
	 */
	float getSightRange();

	/**
	 * @return The health of the entity.
	 */
	int getCurrentHealth();

	/**
	 * @return The maximum health of the entity.
	 */
	int getMaxHealth();

	/**
	 * @return The damage of the entity.
	 */
	int getDamage();

	/**
	 * Deals damage to the entity
	 * 
	 * @param damage
	 *            The damage to be dealt
	 */
	void dealDamageTo(int damage);
}