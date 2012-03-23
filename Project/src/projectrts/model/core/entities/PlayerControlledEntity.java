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
	
	private Player owner;
	
	public PlayerControlledEntity(Position spawnPos, Player owner) {
		super(spawnPos);
		this.owner = owner;
		// TODO Auto-generated constructor stub
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

}
