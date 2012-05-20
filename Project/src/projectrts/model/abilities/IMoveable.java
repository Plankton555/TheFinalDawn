package projectrts.model.abilities;

import projectrts.model.entities.AbstractPlayerControlledEntity;

/**
 * Any ability that will use a MoveAbility internally must implement this.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
interface IMoveable {

	/**
	 * Creates a new ability.
	 * 
	 * @param entity
	 *            The PlayerControlledEntity that is connected to this ability.
	 * @param moveAbility
	 *            The MoveAbility that the entity is using.
	 * @return The new ability.
	 */
	AbstractAbility createAbility(AbstractPlayerControlledEntity entity,
			MoveAbility moveAbility);
}