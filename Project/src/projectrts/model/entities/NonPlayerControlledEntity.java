package projectrts.model.entities;

import projectrts.model.world.Position;

/**
 * Abstract class for the common part of different non player controlled
 * entities
 * 
 * @author Jakob Svensson
 * 
 */
// TODO Jakob: PMD: Abstract classes should be named AbstractXXX
abstract class NonPlayerControlledEntity extends AbstractEntity {

	/**
	 * Concrete version used for creating new instances according to factory
	 * pattern.
	 * 
	 * @return A new instance of this class.
	 */
	public abstract NonPlayerControlledEntity createNPCE(Position pos);
}
