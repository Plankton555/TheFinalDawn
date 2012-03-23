package projectrts.model.core;

/**
 * 
 * @author Filip Brynfors
 *
 */
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
	
	/**
	 * @return The name of the entity
	 */
	public String getName();
}