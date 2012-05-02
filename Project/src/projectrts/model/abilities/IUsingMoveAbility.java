package projectrts.model.abilities;

import projectrts.model.entities.IPlayerControlledEntity;

public interface IUsingMoveAbility {

	/**
	 * Creates a new ability.
	 * @param entity The PlayerControlledEntity that is connected to this ability.
	 * @param moveAbility The MoveAbility that the entity is using.
	 * @return The new ability.
	 */
	public abstract AbstractAbility createAbility(IPlayerControlledEntity entity, MoveAbility moveAbility);
}
