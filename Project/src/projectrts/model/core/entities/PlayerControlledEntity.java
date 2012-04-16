package projectrts.model.core.entities;

import java.util.ArrayList;
import java.util.List;

import projectrts.model.core.IPlayer;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.AbstractAbility;
import projectrts.model.core.abilities.IAbility;

/**
 *  Abstract class for the common part of different  player controlled entities
 * @author Jakob Svensson, Modified by Filip Brynfors, Markus Ekström
 * 
 *
 */
public abstract class PlayerControlledEntity extends AbstractEntity implements IPlayerControlledEntity{
	protected List<AbstractAbility> abilities = new ArrayList<AbstractAbility>();
	private int health;
	private int maxHealth;

	
	private Player owner;
	
	protected PlayerControlledEntity() {
	}
	
	/**
	 * Spawns an entity
	 * @param spawnPos
	 * @param owner
	 */
	protected PlayerControlledEntity(Player owner, Position spawnPos) {
		super(spawnPos);
		this.owner = owner;
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
			//TODO Markus: Set dead? Send event?
		} else {
			health -= amount;
		}
	}
	

	/**
	 * Uses an ability at the given position
	 * @param ability the name of ability to be used
	 * @param pos the position that the ability will be used at
	 */
	public void doAbility(String ability, Position pos) {
		for(AbstractAbility ownAbility: abilities){
			if(ability.equals(ownAbility.getName())){
				ownAbility.useAbility(this, pos);
			}
		}
	}
	
	@Override
	public float getSightRange() {
		// TODO Markus: Implement PCE.getSightRange()
		return 0;
	}

	

	@Override
	public void update(float tpf) {
		for(AbstractAbility ability: abilities){
			ability.update(tpf);
		}
	}

	public abstract PlayerControlledEntity createPCE(Player owner, Position pos);
}
