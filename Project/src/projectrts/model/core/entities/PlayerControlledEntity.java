package projectrts.model.core.entities;

import java.util.ArrayList;
import java.util.List;

import projectrts.model.core.IPlayer;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.IAbility;

/**
 * 
 * @author Jakob Svensson, Modified by Filip Brynfors
 *
 */
public abstract class PlayerControlledEntity extends AbstractEntity implements IPlayerControlledEntity{
	protected List<IAbility> abilities = new ArrayList<IAbility>();
	protected int health;
	private int maxHealth;
	
	private Player owner;
	
	/**
	 * Spawns an entity
	 * @param spawnPos
	 * @param owner
	 * @param maxHealth
	 */
	public PlayerControlledEntity(Position spawnPos, Player owner, int maxHealth) {
		super(spawnPos);
		this.owner = owner;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}
	
	@Override
	public IPlayer getOwner() {
		return owner;
	}
	
	@Override
	public List<IAbility> getAbilities() {
		List<IAbility> retAbilities = new ArrayList<IAbility>();
		for(IAbility ability: abilities){
			retAbilities.add(ability);
		}
		return retAbilities;
	}
	
	@Override
	public int getHealth(){
		return health;
	}

	
	@Override
	public int getMaxHealth(){
		return maxHealth;
	}
	
	/**
	 * Reduces the current health by the provided amount of damage
	 * @param amount the amout the hp is reduced by
	 */
	public void takeDamage(int amount){
		if(amount>=health){
			health = 0;
			//TODO: Set dead? Send event?
		} else {
			health -= amount;
		}
	}
	
	public abstract void doAbility(IAbility ability, Position pos);
}
