package projectrts.model.core.entities;

import projectrts.model.core.Position;

/**
 * 
 * @author Jakob Svensson
 *
 */
public class Resource extends NonPlayerControlledEntity{

	public Resource(Position spawnPos) {
		super(spawnPos);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public float getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(float tpf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NonPlayerControlledEntity createNPCE(Position pos) {
		return null;
	}
	
	public int mine(){
		return 10;
	}

}
