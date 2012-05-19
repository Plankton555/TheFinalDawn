package projectrts.model.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import projectrts.model.world.Position;

/**
 * Abstract class for the common part of different player controlled entities
 * 
 * @author Jakob Svensson, Modified by Filip Brynfors, Markus Ekström
 * 
 * 
 */
// TODO Jakob: PMD: Abstract classes should be named AbstractXXX
public abstract class PlayerControlledEntity extends AbstractEntity implements
		IPlayerControlledEntity {
	private int currentHealth;
	private int maxHealth;
	private float sightRange;
	private Player owner;
	private int damage;
	private PropertyChangeSupport pcs;
	private boolean dead = false;
	private State state = State.IDLE;

	/**
	 * A state for showing if the pce is busy or idle
	 * 
	 * @author Markus Ekström
	 * 
	 */
	public enum State {
		IDLE, BUSY
	};

	/**
	 * When subclassing, invoke this to initialize the entity.
	 * 
	 * @param owner2
	 *            The owner of the entity.
	 * @param spawnPos
	 *            The initial position of the entity.
	 */
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(spawnPos);
		this.owner = owner;
		this.pcs = new PropertyChangeSupport(this);
	}

	// TODO Markus: Add javadoc
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}

	@Override
	public IPlayer getOwner() {
		return owner;
	}

	@Override
	public int getCurrentHealth() {
		return currentHealth;
	}

	@Override
	public int getMaxHealth() {
		return maxHealth;
	}

	@Override
	public void dealDamageTo(int damage) {
		currentHealth -= damage;
		if (currentHealth <= 0) {
			currentHealth = 0;
			setDead();
			EntityManager.INSTANCE.removeEntity(this);
		}
	}

	/**
	 * Returns whether the pce is dead or not
	 * 
	 * @return true if dead, false otherwise
	 */
	public boolean isDead() {
		return dead;
	}

	private void setDead() {
		dead = true;
	}

	@Override
	public float getSightRange() {
		return sightRange;
	}

	@Override
	public int getDamage() {
		return damage;
	}

	/**
	 * Sets the amount damage that the pce deals with each hit
	 * 
	 * @param damage
	 *            the new damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	protected void setMaxHealth(int newMaxHealth) {
		this.maxHealth = newMaxHealth;
		this.currentHealth = newMaxHealth;
	}

	protected void setSightRange(float sightRange) {
		this.sightRange = sightRange;
	}

	/**
	 * Sets the current health of the pce
	 * 
	 * @param newCurrentHealth
	 *            the new health
	 */
	public void setCurrentHealth(int newCurrentHealth) {
		this.currentHealth = newCurrentHealth;
	}

	/**
	 * Returns which state the pce is in
	 * 
	 * @return the current state
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * Sets the current state of the pec
	 * 
	 * @param state
	 *            the new state
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Creates a new Player Controlled Entity
	 * 
	 * @param player
	 *            the owner of the pce
	 * @param pos
	 *            the spawn position
	 * @return the new pce
	 */
	public abstract PlayerControlledEntity createPCE(Player player, Position pos);
}
