package projectrts.model.core;

import java.util.ArrayList;
import java.util.List;

/**
 * The singleton entity manager.
 * @author Bjorn Persson Mattsson
 *
 */
public class EntityManager {

	private static EntityManager instance = new EntityManager();
	
	private List<IEntity> allEntities = new ArrayList<IEntity>();
	
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
	
	/**
	 * Returns all entities that the provided player owns.
	 * @param player Player
	 * @return A list of all entities of player.
	 */
	public List<IEntity> getEntitiesOfPlayer(Player player)
	{
		List<IEntity> output = new ArrayList<IEntity>();
		for (IEntity e : allEntities)
		{
			if (e.getOwner().equals(player))
			{
				output.add(e);
			}
		}
		return output;
	}
	
	public List<IEntity> getAllEntities()
	{
		return allEntities;
	}
}
