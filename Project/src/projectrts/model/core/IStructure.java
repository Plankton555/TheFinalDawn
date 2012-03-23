package projectrts.model.core;

public interface IStructure {
	/**
	 * @return The position of the structure.
	 */
	public Position getPosition();
	
	/**
	 * @return The size of the structure.
	 */
	public float getSize();
	
	/**
	 * @return The owner of the structure.
	 */
	public IPlayer getOwner();
}