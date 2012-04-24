package projectrts.model.entities;

/**
 * Interface for abilities
 * @author Filip Brynfors
 *
 */
public interface IAbility {
	
	/**
	 * Gets the name of the ability
	 * @return The name of the ability.
	 */
	public String getName();
	
	/**
	 * Gets the cooldown of the ability
	 * @return the cooldown
	 */
	public float getCooldown();
	
	
	/**
	 * Gets the remaining time of the cooldown
	 * @return the remaining cooldown
	 */
	public float getRemainingCooldown();
}
