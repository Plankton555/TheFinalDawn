package projectrts.model.core;

/**
 * Abstract class for the common parts of the different entities
 * @author Filip Brynfors
 *
 */
public abstract class AbstractEntity implements IEntity {

	private Player owner;
	protected Position position;
	
	/**
	 * Spawns a entity at the provided position.
	 * @param spawnPos Spawn position
	 * @param owner The owner of the unit
	 */
	public AbstractEntity(Position spawnPos, Player owner){
		this.position = new Position(spawnPos);
		this.owner = owner;
	}
	
	@Override
	public Position getPosition() {
		return position;
	}


	@Override
	public IPlayer getOwner() {
		return owner;
	}
	
	/**
	 * Updates the unit.
	 * @param tpf Time per frame
	 */
	public abstract void update(float tpf);
	
}
