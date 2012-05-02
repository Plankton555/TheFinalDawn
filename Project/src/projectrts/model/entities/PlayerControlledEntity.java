package projectrts.model.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import projectrts.model.entities.abilities.MoveAbility;
import projectrts.model.player.IPlayer;
import projectrts.model.utils.Position;
import projectrts.model.world.INode;
import projectrts.model.world.World;

/**
 *  Abstract class for the common part of different  player controlled entities
 * @author Jakob Svensson, Modified by Filip Brynfors, Markus Ekström
 * 
 *
 */
public abstract class PlayerControlledEntity extends AbstractEntity implements IPlayerControlledEntity{
	protected List<AbstractAbility> abilities = new ArrayList<AbstractAbility>();
	private int currentHealth;
	private int maxHealth;
	private float sightRange;
	private IPlayer owner;
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
	protected void initialize(IPlayer owner2, Position spawnPos) {
		super.initialize(spawnPos);
		this.owner = owner2;
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
	public List<IAbility> getAbilities() {
		List<IAbility> copy = new ArrayList<IAbility>();
		for(IAbility ability: abilities){
			copy.add(ability);
		}
		return copy;
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
			EntityManager.getInstance().removeEntity(this);
		}
	}

	/**
	 * Uses an ability at the given position
	 * @param ability the name of ability to be used
	 * @param pos the position that the ability will be used at
	 */
	public void doAbility(String ability, Position pos) {
		for(AbstractAbility ownAbility: abilities){
			ownAbility.setActive(false); //Make sure that only one ability can be active at once
			ownAbility.setFinished(true);
		}
		for(AbstractAbility ownAbility: abilities){
			if(ability.equals(ownAbility.getClass().getSimpleName())){
				ownAbility.useAbility(pos);
			}
		}
	}
	
	public boolean isDead() {
		return dead;
	}
	
	private void setDead() {
		dead = true;
		INode occupiedNode = World.getInstance().getNodeAt(getPosition());
		for(AbstractAbility ability: abilities){
			ability.setFinished(true);
			if (ability instanceof MoveAbility)
			{
				MoveAbility mAbility = (MoveAbility) ability;
				occupiedNode = mAbility.getOccupiedNode();
			}
		}
		World.getInstance().setNodesOccupied(occupiedNode, getSize(), 0);
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
	
	@Override
	public void update(float tpf) {
		if(!dead) {
			state = State.IDLE;
			for(AbstractAbility ability: abilities){
				ability.update(tpf);
				if(ability.isActive()) {
					state = State.BUSY;
				}
			}
		}
	}
	
	public State getState() {
		return this.state;
	}

	public abstract PlayerControlledEntity createPCE(IPlayer aiPlayer, Position pos);
}
