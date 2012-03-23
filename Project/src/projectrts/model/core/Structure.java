package projectrts.model.core;


/**
 * A simple Structure
 * @author Filip Brynfors
 *
 */
public class Structure implements IStructure  {
	
	private Player owner;
	private Position position;
	
	/**
	 * Spawns a Structure at the provided position.
	 * @param spawnPos Spawn position
	 */
	public Structure(Position spawnPos, Player owner)
	{
		this.position = new Position(spawnPos);
		this.owner = owner;
	}
	
	@Override
	public Position getPosition() {
		return this.position;
	}

	@Override
	public float getSize() {
		// TODO Change this later
		return 1;
	}

	@Override
	public IPlayer getOwner() {
		return owner;
	}
	
	public void update(float tpf)
	{
		//TODO: Add a micro AI for attacking
	}
}
