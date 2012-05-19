package projectrts.model.entities;

import projectrts.model.world.INode;
import projectrts.model.world.IWorld;
import projectrts.model.world.Position;
import projectrts.model.world.World;

/**
 * Abstract class for the common parts of the different entities
 * 
 * @author Filip Brynfors, Modified by Markus Ekström, Jakob Svensson, Bjorn
 *         Persson Mattsson
 * 
 */
public abstract class AbstractEntity implements IEntity {

	private Position position;

	private String name;
	private int entityID;
	private IWorld world;
	private float size;
	private float speed;

	/**
	 * When subclassing, invoke this to initialize the entity.
	 * 
	 * @param spawnPos
	 *            The initial position of the entity.
	 */
	protected void initialize(Position spawnPos) {
		this.entityID = EntityManager.INSTANCE.requestNewEntityID();

		this.world = World.INSTANCE;

		this.position = spawnPos.copy();
	}

	protected void setSize(float size) {
		this.size = size;
		occupyNodes(world.getNodeAt(this.getPosition()));
	}

	protected void setSpeed(float speed) {
		this.speed = speed;
	}

	protected void setName(String name) {
		this.name = name;
	}

	@Override
	public float getSize() {
		return size;
	}

	@Override
	public float getSpeed() {
		return speed;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public int getEntityID() {
		return entityID;
	}

	/**
	 * Sets the position of the entity
	 * 
	 * @param pos
	 *            the new position
	 */
	public void setPosition(Position pos) {
		position = pos.copy();
	}

	private void occupyNodes(INode newNode) {
		world.setNodesOccupied(newNode, getSize(), getEntityID());
	}
}