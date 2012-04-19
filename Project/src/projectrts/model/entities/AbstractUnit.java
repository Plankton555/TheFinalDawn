package projectrts.model.entities;

import projectrts.model.player.Player;
import projectrts.model.utils.Position;

public abstract class AbstractUnit extends PlayerControlledEntity{

	
	protected AbstractUnit() {
	}
	
	/**
	 * Spawns a unit
	 * @param spawnPos
	 * @param owner
	 */
	protected AbstractUnit(Player owner, Position spawnPos) {
		super(owner, spawnPos);
	}
}
