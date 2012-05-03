package projectrts.model.entities;

import java.beans.PropertyChangeListener;
import java.util.List;

import projectrts.model.world.Position;

//TODO Markus: ADD JAVADOC!!
public interface IEntityManager {
	
	public List<IEntity> getAllEntities();
	
	public List<IPlayerControlledEntity> getEntitiesOfPlayer(Player player);
	
	public void select(Position pos, Player owner);

	/**
	 * Returns the selected entities
	 * @return A list with the selected entities
	 */
	public List<IEntity> getSelectedEntities();
	
	public List<IPlayerControlledEntity> getSelectedEntitiesOfPlayer(Player player);
	
	public void addListener(PropertyChangeListener pcl);
	
	
}
