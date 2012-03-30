package projectrts.model.core.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import projectrts.model.core.Player;
import projectrts.model.core.Position;

/**
 * Abstract class for the common parts of the different entities
 * @author Filip Brynfors, Modified by Markus Ekström, Jakob Svensson
 *
 */
public abstract class AbstractEntity implements IEntity {

	protected Position position;
	private String name;
	private float size;
	
	protected AbstractEntity() {
	}
	
	/**
	 * Spawns a entity at the provided position.
	 * @param spawnPos Spawn position
	 * @param owner The owner of the unit
	 */
	protected AbstractEntity(Position spawnPos){
		this.position = new Position(spawnPos);
		
	}
	
	protected void setSize(float size){
		this.size=size;
	}
	protected void setName(String name) {
		this.name = name;
	}
	
	@Override
	public float getSize() {
		return size;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Sets the position of the entity
	 * @param pos the new position
	 */
	public void setPosition(Position pos){
		position = pos.clone();
	}
	
	/**
	 * Updates the unit.
	 * @param tpf Time per frame
	 */
	public abstract void update(float tpf);
}
