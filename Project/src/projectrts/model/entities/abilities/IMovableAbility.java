package projectrts.model.entities.abilities;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.PlayerControlledEntity;

public interface IMovableAbility {

	/**
	 * Creates a new ability.
	 * @param entity The PlayerControlledEntity that is connected to this ability.
	 * @param moveAbility The MoveAbility that the entity is using.
	 * @return The new ability.
	 */
	public abstract AbstractAbility createAbility(PlayerControlledEntity entity, MoveAbility moveAbility);
}
