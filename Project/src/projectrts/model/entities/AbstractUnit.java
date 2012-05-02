package projectrts.model.entities;

import projectrts.model.player.IPlayer;
import projectrts.model.utils.Position;

/**
 * An abstract class for units
 * @author Jakob Svensson
 *
 */
public abstract class AbstractUnit extends PlayerControlledEntity{

	/**
	 * When subclassing, invoke this to initialize the entity.
	 * @param owner The owner of the entity.
	 * @param spawnPos The initial position of the entity.
	 */
	protected void initialize(IPlayer owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
	}
}
