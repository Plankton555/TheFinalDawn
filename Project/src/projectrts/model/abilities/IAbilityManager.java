package projectrts.model.abilities;

import java.util.List;

import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.player.IPlayer;
import projectrts.model.utils.Position;

public interface IAbilityManager {

	public abstract List<IAbility> getAbilities(IPlayerControlledEntity entity);

	/**
	 * Uses an ability at the given position
	 * @param ability the name of ability to be used
	 * @param pos the position that the ability will be used at
	 */
	public abstract void doAbility(String ability, Position pos,
			IPlayerControlledEntity entity);

	/**
	 * Uses the abilities of the selected entities
	 * @param ability the ability to be used
	 * @param p the position to use the ability at
	 * @param owner Owner of the entities that will do something.
	 */
	public abstract void useAbilitySelected(String ability, Position p, IPlayer owner);

}