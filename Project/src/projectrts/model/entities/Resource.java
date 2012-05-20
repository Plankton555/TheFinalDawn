package projectrts.model.entities;

import projectrts.model.world.Position;

/**
 * A resource to be gathered
 * 
 * @author Jakob Svensson
 * 
 */
public class Resource extends AbstractNonPlayerControlledEntity {

	private static float size = 1;
	private static int mineAmount = 4;
	static {
		EntityFactory.registerNPCE(Resource.class.getSimpleName(),
				new Resource());
	}

	/**
	 * Initializes the resource
	 */
	protected void initialize(Position spawnPos) {
		super.initialize(spawnPos);
		setName(Resource.class.getSimpleName());
		setSize(size);
	}

	@Override
	public AbstractNonPlayerControlledEntity createNPCE(Position pos) {
		Resource newResource = new Resource();
		newResource.initialize(pos);
		return newResource;
	}

	/**
	 * Returns the the amount of resources the Resource gives each mine.
	 * 
	 * @return the amount of resources
	 */
	public int mine() {
		return mineAmount;
	}
}