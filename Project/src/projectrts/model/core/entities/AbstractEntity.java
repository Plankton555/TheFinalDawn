package projectrts.model.core.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import projectrts.model.core.Player;
import projectrts.model.core.Position;

/**
 * Abstract class for the common parts of the different entities
 * @author Filip Brynfors, Modified by Markus Ekström
 *
 */
public abstract class AbstractEntity implements IEntity {

	protected Position position;
	
	/**
	 * Spawns a entity at the provided position.
	 * @param spawnPos Spawn position
	 * @param owner The owner of the unit
	 */
	public AbstractEntity(Position spawnPos){
		this.position = new Position(spawnPos);
		
	}
	
	@Override
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Updates the unit.
	 * @param tpf Time per frame
	 */
	public abstract void update(float tpf);
}
