package projectrts.model.entities;

import projectrts.model.world.Position;

/**
 * A building for training warriors 
 * @author Jakob Svensson
 *
 */
public class Barracks extends AbstractStructure{
	
	private static float size = 3;
	//TODO Jakob: PMD error, "Variables that are final and static should be all in caps"
	private static final float sightRange = 5;
	private static int maxHealth = 1000;
	
	static {
		EntityFactory.INSTANCE.registerPCE(Barracks.class.getSimpleName(), new Barracks());
	}
	
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		this.setName(Barracks.class.getSimpleName());
		this.setSize(size);
		this.setSightRange(sightRange);
		this.setMaxHealth(maxHealth);		
		deposit = false;
	}
	
	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		Barracks newBarracks = new Barracks();
		newBarracks.initialize(owner, pos);
		return newBarracks;
	}

}
