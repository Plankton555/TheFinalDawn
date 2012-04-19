package projectrts.model.ai;

import projectrts.model.entities.units.Unit;


/**
 * The "brain" of a unit.
 * @author Bjorn Persson Mattsson
 *
 */
public class MicroAI {

	// TODO Markus: Implement MicroAI (if it's going to be used)
	private Unit myUnit;
	
	/**
	 * Creates a micro AI for the provided unit.
	 * @param unit The unit
	 */
	public MicroAI(Unit unit) {
		this.myUnit = unit;
	}
}
