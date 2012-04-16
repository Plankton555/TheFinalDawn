package projectrts.model.core.entities;

import projectrts.model.core.EntityFactory;
import projectrts.model.core.Position;

/**
 * A resource to be gathered
 * @author Jakob Svensson
 *
 */
public class Resource extends NonPlayerControlledEntity{
	

	private static String name = "Resource";
	private static float size = 1f;
	static {
		EntityFactory.INSTANCE.registerNPCE(name, new Resource());
	}
	
	public Resource(){
		
	}
	
	public Resource(Position spawnPos) {
		super(spawnPos);
		setName(name);
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
		return 10;
	}

}
