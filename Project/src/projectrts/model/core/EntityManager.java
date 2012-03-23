package projectrts.model.core;

import java.util.ArrayList;
import java.util.List;


import javax.vecmath.Vector2d;

import projectrts.model.core.entities.IEntity;

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
	 * @param entity The entity from which to check.
	 * @return List of all entities close to position.
	 */
	public List<IEntity> getNearbyEntities(IEntity entity)
	{
		// Preliminary method. May need to be changed to optimize.
		
		List<IEntity> output = new ArrayList<IEntity>();
		
		for (IEntity e : allEntities)
		{
			Vector2d distance = new Vector2d(
					e.getPosition().getX()-entity.getPosition().getX(),
					e.getPosition().getY()-entity.getPosition().getY());
			if (distance.length() - (e.getSize()/2) <= entity.getRange())
			{
				output.add(e);
			}
		}
		return output;
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
	
	/**
	 * @return All entities.
	 */
	public List<IEntity> getAllEntities()
	{
		return allEntities;
	}
	
	/**
	 * Adds an entity to the EntityManager.
	 * This does not keep track of multiple copies of the same entity.
	 * @param entity The entity.
	 */
	public void addEntity(IEntity entity)
	{
		allEntities.add(entity);
	}
}
