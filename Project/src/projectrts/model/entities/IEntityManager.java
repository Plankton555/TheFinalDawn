package projectrts.model.entities;

import java.beans.PropertyChangeListener;
import java.util.List;

import projectrts.model.world.Position;

//TODO Markus: ADD JAVADOC!!
public interface IEntityManager {
	
	public List<IEntity> getAllEntities();
	
	public List<IPlayerControlledEntity> getEntitiesOfPlayer(IPlayer player);
	
	public void select(Position pos, IPlayer iModelPlayer);

	/**
	 * Returns the selected entities
	 * @return A list with the selected entities
	 */
	public List<IEntity> getSelectedEntities();
	
	public List<IPlayerControlledEntity> getSelectedEntitiesOfPlayer(IPlayer player);
	
	public void addListener(PropertyChangeListener pcl);

	public IPlayerControlledEntity getPCEAtPosition(Position pos);

	public IEntity getNPCEAtPosition(Position pos);
	
	
}
