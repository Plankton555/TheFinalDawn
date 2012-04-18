package projectrts.model.core.entities;

import projectrts.model.core.Position;
import projectrts.model.core.pathfinding.Node;

/**
 * Abstract class for the common parts of the different entities
 * @author Filip Brynfors, Modified by Markus Ekström, Jakob Svensson, Bjorn Persson Mattsson
 *
 */
public abstract class AbstractEntity implements IEntity {

	private Position position;
	private String name;
	private float size;
	private Node occupiedNode;
	
	protected AbstractEntity() {
	}
	
	/**
	 * Spawns a entity at the provided position.
	 * @param spawnPos Spawn position
	 * @param owner The owner of the unit
	 */
	protected AbstractEntity(Position spawnPos){
		this.setPosition(spawnPos);
		
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
		enterNewNode(aStar.getWorld().getNodeAt(pos));
	}

	private void enterNewNode(Node newNode)
	{
		System.out.println("Entering node: " + newNode.getPosition());
		// TODO Plankton: Add support for sizes here
		occupiedNode.setOccupied(false);
		newNode.setOccupied(true);
		occupiedNode = newNode;
	}
	
	/**
	 * Updates the unit.
	 * @param tpf Time per frame
	 */
	public abstract void update(float tpf);
}
