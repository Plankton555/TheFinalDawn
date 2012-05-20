package projectrts.model.abilities;

import projectrts.model.entities.AbstractPlayerControlledEntity;

/**
 * Any ability that will NOT use a MoveAbility internally must implement this.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
interface IStationaryAbility {

	/**
	 * Creates a new ability.
	 * 
	 * @param entity
	 *            The PlayerControlledEntity that is connected to this ability.
	 * @return The new ability.
	 */
	AbstractAbility createAbility(AbstractPlayerControlledEntity entity);
}