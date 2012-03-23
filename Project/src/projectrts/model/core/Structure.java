package projectrts.model.core;


/**
 * A simple Structure
 * @author Filip Brynfors
 *
 */
public class Structure extends AbstractEntity {
	
	
	
	/**
	 * Spawns a Structure at the provided position.
	 * @param spawnPos Spawn position
	 */
	public Structure(Position spawnPos, Player owner){
		super(spawnPos, owner);
	}
	


	@Override
	public float getSize() {
		// TODO Change this later
		return 1;
	}


	
	public void update(float tpf){
		//TODO: Add a micro AI for attacking
	}



	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
