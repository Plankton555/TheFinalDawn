package projectrts.model.core.entities;

import projectrts.model.core.Position;

public abstract class NonPlayerControlledEntity extends AbstractEntity {
	
	public NonPlayerControlledEntity(Position spawnPos) {
		super(spawnPos);
	}

	/**
	 * Concrete version used for creating new instances according to
	 * factory pattern.
	 * @return A new instance of this class.
	 */
	public abstract NonPlayerControlledEntity createNPCE(Position pos);
}
