package projectrts.model.abilities;

import projectrts.model.entities.PlayerControlledEntity;



public interface INotUsingMoveAbility {

	/**
	 * Creates a new ability.
	 * @param entity The PlayerControlledEntity that is connected to this ability.
	 * @return The new ability.
	 */
	public abstract AbstractAbility createAbility(PlayerControlledEntity entity);
}