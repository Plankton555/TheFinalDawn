package projectrts.core;

/**
 * The "brain" of a unit.
 * @author Bjorn Persson Mattsson
 *
 */
public class MicroAI {

	private Unit myUnit;
	
	public MicroAI(Unit unit) {
		this.myUnit = unit;
	}

	/**
	 * Determines the next step towards the target.
	 * @param target The target position.
	 * @param tpf Time per frame.
	 * @return The position of the next step towards the target.
	 */
	public Position determinePath(Position target, float tpf)
	{
		// TODO MicroAI.determinePath()
		return null;
	}
}
