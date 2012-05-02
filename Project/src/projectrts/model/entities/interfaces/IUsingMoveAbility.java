package projectrts.model.entities.interfaces;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.abilities.MoveAbility;

public interface IUsingMoveAbility {

	/**
	 * Creates a new ability.
	 * @param entity The PlayerControlledEntity that is connected to this ability.
	 * @param moveAbility The MoveAbility that the entity is using.
	 * @return The new ability.
	 */
	public abstract AbstractAbility createAbility(IPlayerControlledEntity entity, MoveAbility moveAbility);
}
