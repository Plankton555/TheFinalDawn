package projectrts.model.entities;

import projectrts.model.world.Position;
/**
 * Abstract class for the common part of different non player controlled entities
 * @author Jakob Svensson
 *
 */
public abstract class NonPlayerControlledEntity extends AbstractEntity {
	
	/**
	 * When subclassing, invoke this to initialize the entity.
	 * @param spawnPos The initial position of the entity.
	 */
	protected void initialize(Position spawnPos) {
		super.initialize(spawnPos);
	}

	/**
	 * Concrete version used for creating new instances according to
	 * factory pattern.
	 * @return A new instance of this class.
	 */
	public abstract NonPlayerControlledEntity createNPCE(Position pos);
}
