package projectrts.model.entities;

import projectrts.model.world.Position;

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
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
	}
}
