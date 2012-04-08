package projectrts.model.core;

import projectrts.model.core.entities.Unit;

/**
 * The "brain" of a unit.
 * @author Bjorn Persson Mattsson
 *
 */
public class MicroAI {

	// TODO Anyone: Implement MicroAI (if it's going to be used)
	private Unit myUnit;
	
	/**
	 * Creates a micro AI for the provided unit.
	 * @param unit The unit
	 */
	public MicroAI(Unit unit) {
		this.myUnit = unit;
	}
}
