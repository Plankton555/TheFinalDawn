package projectrts.model.ai;

import projectrts.model.entities.PlayerControlledEntity;


/**
 * The "brain" of a unit.
 * @author Bjorn Persson Mattsson
 *
 */
public class MicroAI {

	// TODO Markus: Implement MicroAI (if it's going to be used)
	private PlayerControlledEntity myPCE;
	
	/**
	 * Creates a micro AI for the provided unit.
	 * @param unit The unit
	 */
	public MicroAI(PlayerControlledEntity pce) {
		this.myPCE = pce;
	}
}
