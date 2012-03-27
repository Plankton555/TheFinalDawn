package projectrts.model.core.entities;

import java.util.ArrayList;
import java.util.List;

import projectrts.model.core.IPlayer;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.AbstractAbility;
import projectrts.model.core.abilities.IAbility;

/**
 * 
 * @author Jakob Svensson, Modified by Filip Brynfors, Markus Ekström
 * 
 *
 */
public abstract class PlayerControlledEntity extends AbstractEntity implements IPlayerControlledEntity{
	protected List<AbstractAbility> abilities = new ArrayList<AbstractAbility>();
	private int health;
	private int maxHealth;
	private String name;
	
	private Player owner;
	
	/**
	 * Spawns an entity
	 * @param spawnPos
	 * @param owner
	 */
	protected PlayerControlledEntity(Player owner, Position spawnPos) {
		super(spawnPos);
		this.owner = owner;
	}
	
	protected void setName(String name) {
		this.name = name;
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
	

	public void doAbility(IAbility ability, Position pos) {
		for(AbstractAbility ownAbility: abilities){
			if(ability.getName() == ownAbility.getName()){
				ownAbility.useAbility(this, pos);
			}
		}
	}
	
	@Override
	public float getSightRange() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void update(float tpf) {
		// TODO Auto-generated method stub
		
	}

	public abstract PlayerControlledEntity createPCE(Player owner, Position pos);
}
