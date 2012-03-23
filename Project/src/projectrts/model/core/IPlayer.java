package projectrts.model.core;

import java.util.List;

import projectrts.model.core.entites.IEntity;

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
	 * Gives a move order to all selected units.
	 * @param p The position for the units to move to.
	 */
	public void moveSelectedTo(Position p);
	
	
	/**
	 * @return A list of selected entities.
	 */
	public List<IEntity> getSelectedEntities();
}
