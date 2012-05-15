package projectrts.model.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import projectrts.model.world.Position;

/**
 *  Abstract class for the common part of different  player controlled entities
 * @author Jakob Svensson, Modified by Filip Brynfors, Markus Ekström
 * 
 *
 */
public abstract class PlayerControlledEntity extends AbstractEntity implements IPlayerControlledEntity{
	private int currentHealth;
	private int maxHealth;
	private float sightRange;
	private Player owner;
	private int damage;
	private PropertyChangeSupport pcs;
	private boolean dead = false;
	private State state = State.IDLE;
	
	public enum State{IDLE, BUSY};
	
	/**
	 * When subclassing, invoke this to initialize the entity.
	 * @param owner2 The owner of the entity.
	 * @param spawnPos The initial position of the entity.
	 */
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(spawnPos);
		this.owner = owner;
		this.pcs = new PropertyChangeSupport(this);
	}
	
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
	
	
	@Override
	public IPlayer getOwner() {
		return owner;
	}
	
	@Override
	public int getCurrentHealth(){
		return currentHealth;
	}

	@Override
	public int getMaxHealth(){
		return maxHealth;
	}
	
	@Override
	public void dealDamageTo(int damage) {
		currentHealth -= damage;
		if(currentHealth <= 0){
			currentHealth = 0;
			setDead();
			EntityManager.INSTANCE.removeEntity(this);
		}
	}

	public boolean isDead() {
		return dead;
	}
	
	private void setDead() {
		dead = true;
	}
	
	@Override
	public float getSightRange(){
		return sightRange;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public void setMaxHealth(int newMaxHealth) {
		this.maxHealth = newMaxHealth;
		this.currentHealth = newMaxHealth;
	}
	
	protected void setSightRange(float sightRange){
		this.sightRange = sightRange;
	}
	
	public void setCurrentHealth(int newCurrentHealth) {
		this.currentHealth = newCurrentHealth;
	}
	
	public State getState() {
		return this.state;
	}
	
	public void setState(State state) {
		this.state = state;
	}

	public abstract PlayerControlledEntity createPCE(Player aiPlayer, Position pos);
}
