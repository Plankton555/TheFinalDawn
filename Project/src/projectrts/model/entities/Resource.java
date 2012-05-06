package projectrts.model.entities;

import projectrts.model.world.Position;

/**
 * A resource to be gathered
 * @author Jakob Svensson
 *
 */
public class Resource extends NonPlayerControlledEntity{
	
	private static float size = 1;
	// TODO Jakob: PMD: It is somewhat confusing to have a field name with the same name as a method
	private static int mine = 4;
	static {
		EntityFactory.INSTANCE.registerNPCE(Resource.class.getSimpleName(), new Resource());
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
	public void update(float tpf) {
		// Do nothing yet
		
	}
	
	@Override
	public NonPlayerControlledEntity createNPCE(Position pos) {
		Resource newResource = new Resource();
			newResource.initialize(pos);
		return newResource;
	}
	
	
	/**
	 * Returns the the amount of resources the Resource gives each mine.
	 * @return the amount of resources 
	 */
	public int mine(){
		return mine;
	}

}
