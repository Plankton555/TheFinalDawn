package projectrts.model.abilities;

import java.beans.PropertyChangeListener;
import java.util.List;

import projectrts.model.entities.IPlayer;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.world.Position;

/**
 * The interface for the abilityManager.
 * @author Markus Ekström
 *
 */
public interface IAbilityManager {

	/**
	 * Returns all abilities that are associated with the given entity.
	 * @param entity The entity whose abilities you want.
	 * @return A list of the abilities associated with the entity.
	 */
	List<IAbility> getAbilities(IPlayerControlledEntity entity);

	/**
	 * Uses an ability at the given position
	 * @param ability the name of ability to be used
	 * @param pos the position that the ability will be used at
	 */
	void doAbility(String ability, Position pos,
			IPlayerControlledEntity entity);

	/**
	 * Uses the abilities of the selected entities
	 * @param ability the ability to be used
	 * @param p the position to use the ability at
	 * @param owner Owner of the entities that will do something.
	 */
	void useAbilitySelected(String ability, Position p, IPlayer owner);
	
	/**
	 * @return A list containing the names of the existing abilities.
	 */
	List<String> getExistingAbilityNames();
	
	/**
	 * Adds the pcl to the AbilityManager as a listener.
	 * @param pcl The PropertyChangeListener you want as listener.
	 */
	void setPropertyChangeLister(PropertyChangeListener pcl);

	/**
	 * If the entity is paired with an ability which class is named like abilityName, 
	 * then that ability is aborted.
	 * @param abilityName The simple name of the ability's class.
	 * @param entity The entity the ability is paired with.
	 */
	void abortAbility(String abilityName, IPlayerControlledEntity entity);
}