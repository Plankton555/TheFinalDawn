package projectrts.model.entities;

import projectrts.model.world.Position;

/**
 * A building for blocking spaces
 * 
 * @author Jakob Svensson
 * 
 */
public class Wall extends AbstractStructure {

	public static final float SIZE = 1;
	private static final float SIGHT_RANGE = 5;
	private static int maxHealth = 500;

	static {
		EntityFactory.registerPCE(Wall.class.getSimpleName(), new Wall());
	}

	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		this.setName(Wall.class.getSimpleName());
		this.setSize(SIZE);
		this.setSightRange(SIGHT_RANGE);
		this.setMaxHealth(maxHealth);
		deposit = false;
	}

	@Override
	public AbstractPlayerControlledEntity createPCE(Player owner, Position pos) {
		Wall newBarracks = new Wall();
		newBarracks.initialize(owner, pos);
		return newBarracks;
	}
}