package projectrts.model.core;

import java.util.List;

/**
 * The singleton entity manager.
 * @author Bjorn Persson Mattsson
 *
 */
public class EntityManager {

	private static EntityManager instance = new EntityManager();
	
	/**
	 * @return The instance of this class.
	 */
	public static EntityManager getInstance()
	{
		return instance;
	}
	
	/**
	 * Returns a list of all entities close to the position.
	 * Returns all entities that can be seen from the circle with center in 'p'
	 * and the radius 'range'.
	 * @param p The position
	 * @param range The distance from the position.
	 * @return List of all entities close to position.
	 */
	public List<IEntity> getNearbyEntities(Position p, float range)
	{
		// TODO Implement this
		return null;
	}
	/*
	mappnign
	*/
}
