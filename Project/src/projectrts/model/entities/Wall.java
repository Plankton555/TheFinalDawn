package projectrts.model.entities;

import projectrts.model.world.Position;

/**
 * A building for blocking spaces 
 * @author Jakob Svensson
 *
 */
public class Wall extends AbstractStructure{
	
	private static float size = 1;
	// TODO Jakob: PMD error, "Variables that are final and static should be all in caps"
	private static final float sightRange = 5;
	private static int maxHealth = 500;
	
	static {
		EntityFactory.INSTANCE.registerPCE(Wall.class.getSimpleName(), new Wall());
	}
	
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		this.setName(Wall.class.getSimpleName());
		this.setSize(size);
		this.setSightRange(sightRange);
		this.setMaxHealth(maxHealth);		
		deposit = false;
	}
	
	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		Wall newBarracks = new Wall();
		newBarracks.initialize(owner, pos);
		return newBarracks;
	}

}
