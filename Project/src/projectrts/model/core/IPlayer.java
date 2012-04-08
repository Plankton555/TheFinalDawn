package projectrts.model.core;

import java.util.List;

import projectrts.model.core.entities.IEntity;

/**
 * 
 * @author Bjorn Persson Mattsson
 *
 */
public interface IPlayer {

	/**
	 * Selects the unit on position p.
	 * @param p Position of the unit to be selected.
	 */
	public void select(Position p);
	
	
	/**
	 * @return A list of selected entities.
	 */
	public List<IEntity> getSelectedEntities();

	/**
	 * Gives an order for all selected units to use the given ability
	 * @param ability the name of the ability that will be used
	 * @param p the position to use the ability at
	 */
	public void useAbilitySelected(String ability, Position p);
}
