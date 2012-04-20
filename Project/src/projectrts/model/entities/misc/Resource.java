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
	
	public Resource(){
		
	}
	
	public Resource(Position spawnPos) {
		super(spawnPos);
		setName(Resource.class.getSimpleName());
		setSize(size);
	}

	@Override
	public void update(float tpf) {
		// Do nothing yet
		
	}

	@Override
	public NonPlayerControlledEntity createNPCE(Position pos) {
		return new Resource(pos);
	}
	
	// TODO Jakob: Add javadoc
	public int mine(){
		return 4;
	}

}
