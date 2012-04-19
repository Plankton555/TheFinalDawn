package projectrts.model.entities;

import projectrts.model.utils.Position;
/**
 * Abstract class for the common part of different non player controlled entities
 * @author Jakob Svensson
 *
 */
public abstract class NonPlayerControlledEntity extends AbstractEntity {
	
	
	protected NonPlayerControlledEntity(){
		
	}
	
	protected NonPlayerControlledEntity(Position spawnPos) {
		super(spawnPos);
	}

	/**
	 * Concrete version used for creating new instances according to
	 * factory pattern.
	 * @return A new instance of this class.
	 */
	public abstract NonPlayerControlledEntity createNPCE(Position pos);
}
