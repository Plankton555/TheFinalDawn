package projectrts.model.entities.misc;

import projectrts.model.entities.EntityFactory;
import projectrts.model.entities.NonPlayerControlledEntity;
import projectrts.model.utils.Position;

/**
 * A resource to be gathered
 * @author Jakob Svensson
 *
 */
public class Resource extends NonPlayerControlledEntity{
	
	private static float size = 1;
	static {
		EntityFactory.INSTANCE.registerNPCE(Resource.class.getSimpleName(), new Resource());
	}
	
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
	
	// TODO Jakob: Add javadoc
	public int mine(){
		return 4;
	}

}
