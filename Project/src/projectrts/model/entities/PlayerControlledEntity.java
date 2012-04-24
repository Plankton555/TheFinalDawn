package projectrts.model.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import projectrts.model.constants.P;
import projectrts.model.player.IPlayer;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;

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
	private Player owner;
	private int damage;
	private PropertyChangeSupport pcs;
	
	/**
	 * When subclassing, invoke this to initialize the entity.
	 * @param owner The owner of the entity.
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
	
	public void dealDamageTo(int damage) {
		currentHealth -= damage;
		if(currentHealth <= 0){
			currentHealth = 0;
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
		}
		for(AbstractAbility ownAbility: abilities){
			if(ability.equals(ownAbility.getName())){
				ownAbility.useAbility(pos);
			}
		}
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
	}
	
	protected void setSightRange(float sightRange){
		this.sightRange = sightRange*P.INSTANCE.getUnitLength();
	}
	
	public void setCurrentHealth(int newCurrentHealth) {
		this.currentHealth = newCurrentHealth;
	}
	
	@Override
	public void update(float tpf) {
		for(AbstractAbility ability: abilities){
			ability.update(tpf);
		}
	}

	public abstract PlayerControlledEntity createPCE(Player owner, Position pos);
}
