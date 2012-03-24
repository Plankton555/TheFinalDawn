package projectrts.model.core.abilities;

/**
 * Inteface for abilities
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
}
