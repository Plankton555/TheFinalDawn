package projectrts.model.core;

/**
 * 
 * @author Bjorn Persson Mattsson
 *
 */
public interface IUnit {

	/**
	 * @return The position of the unit.
	 */
	public Position getPosition();
	
	/**
	 * @return The size of the unit.
	 */
	public float getSize();
	
	/**
	 * @return The unit's owner.
	 */
	public IPlayer getOwner();
}
