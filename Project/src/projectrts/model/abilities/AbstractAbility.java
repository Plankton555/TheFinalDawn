package projectrts.model.abilities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import projectrts.model.entities.AbstractPlayerControlledEntity;
import projectrts.model.world.Position;

/**
 * An abstract class for the common attributes of the different abilities
 * 
 * @author Filip Brynfors
 * 
 */
abstract class AbstractAbility implements IAbility {
	private boolean finished = false;
	private boolean active = false;
	private float cooldown;
	private float remainingCooldown = 0;
	protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	protected AbstractPlayerControlledEntity entity;

	/**
	 * Sets the max cooldown of the ability
	 * 
	 * @param cooldown
	 */
	public void setCooldown(float cooldown) {
		this.cooldown = cooldown;
	}

	@Override
	public float getCooldown() {
		return cooldown;
	}

	@Override
	public float getRemainingCooldown() {
		return remainingCooldown;
	}

	/**
	 * Updates the ability
	 */
	public abstract void update(float tpf);

	/**
	 * Updates the remaining cooldown of the ability
	 * 
	 * @param tpf
	 *            the time that cd will be reduced by
	 */
	protected void updateCooldown(float tpf) {
		if (remainingCooldown < tpf) {
			remainingCooldown = 0;
		} else {
			remainingCooldown -= tpf;
		}
	}

	/**
	 * Returns whether the ability is finnished or not
	 * 
	 * @return true if finnished, false otherwise
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Returns whether the ability is active or not
	 * 
	 * @return true if active, false otherwise
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the ability to become active or not
	 * 
	 * @param b
	 */
	public void setActive(boolean b) {
		active = b;
	}

	/**
	 * Sets the ability to become finnished or not
	 * 
	 * @param b
	 */
	public void setFinished(boolean b) {
		finished = b;
		if (finished) {
			active = false;
		}
	}

	/**
	 * Sets the remaining cooldown to be the max cooldown
	 */
	protected void setAbilityUsed() {
		remainingCooldown = cooldown;
	}

	/**
	 * Use the ability at the specified target
	 * 
	 * @param target
	 *            the position where the ability should be cast at
	 */
	public abstract void useAbility(Position target);

	/**
	 * Aborts an ability so it wont execute anymore
	 */
	public void abortAbility() {
		if (isActive()) {
			setFinished(true);
		}
	}

	/**
	 * Adds a listener to the ability
	 * 
	 * @param pcl
	 *            the listener
	 */
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
}