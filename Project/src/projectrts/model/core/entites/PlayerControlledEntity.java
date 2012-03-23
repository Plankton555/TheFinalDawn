package projectrts.model.core.entites;

import projectrts.model.core.IPlayer;
import projectrts.model.core.Player;
import projectrts.model.core.Position;

/**
 * 
 * @author Jakob Svensson
 *
 */
public abstract class PlayerControlledEntity extends AbstractEntity implements IPlayerControlledEntity{
	
	
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

}
