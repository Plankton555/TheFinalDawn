package projectrts.model.core;

import projectrts.model.core.entities.Unit;

/**
 * The "brain" of a unit.
 * @author Bjorn Persson Mattsson
 *
 */
public class MicroAI {

	private Unit myUnit;
	
	/**
	 * Creates a micro AI for the provided unit.
	 * @param unit The unit
	 */
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
		// TODO Extremely simple path algorithm
		float stepSize = P.INSTANCE.getUnitLength()*tpf;
		Position myPos = myUnit.getPosition();
		double newX = 0;
		double newY = 0;
		
		// For x axis
		if (Math.abs(myPos.getX() - target.getX()) < stepSize)
		{
			newX = target.getX();
		}
		else if (myPos.getX() < target.getX())
		{
			newX = myPos.getX()+stepSize;
		}
		else// if (myPos.getX() > target.getX())
		{
			newX = myPos.getX()-stepSize;
		}
		
		// For y axis
		if (Math.abs(myPos.getY() - target.getY()) < stepSize)
		{
			newY = target.getY();
		}
		else if (myPos.getY() < target.getY())
		{
			newY = myPos.getY()+stepSize;
		}
		else// if (myPos.getY() > target.getY())
		{
			newY = myPos.getY()-stepSize;
		}
		
		return new Position(newX, newY);
	}
}
