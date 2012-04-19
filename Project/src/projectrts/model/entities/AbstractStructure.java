package projectrts.model.entities;

import projectrts.model.player.Player;
import projectrts.model.utils.Position;

public abstract class AbstractStructure extends PlayerControlledEntity{
	protected boolean deposit = false;
	
	
	protected AbstractStructure() {
	}
	
	/**
	 * Spawns a structure.
	 * @param spawnPos
	 * @param owner
	 */
	protected AbstractStructure(Player owner, Position spawnPos) {
		super(owner, spawnPos);
	}
	
	public boolean isDeposit() {
		return deposit;
	}
}
