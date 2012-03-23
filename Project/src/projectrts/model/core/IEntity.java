package projectrts.model.core;

public interface IEntity {
	/**
	 * @return The position of the entity.
	 */
	public Position getPosition();
	
	/**
	 * @return The size of the entity.
	 */
	public float getSize();
	
	/**
	 * @return The owner of the entity.
	 */
	public IPlayer getOwner();
}