package projectrts.model.entities;

import projectrts.model.world.Position;
/**
 * A building for creating workers and deposit resources
 * @author Jakob Svensson
 *
 */
public class Headquarter extends AbstractStructure{
	
	// TODO Jakob: Add javadoc (field SIZE since it's public)
	public static final float SIZE = 3;
	private static final float SIGHT_RANGE = 5;
	private static int maxHealth = 2000;
	
	static {
		EntityFactory.registerPCE(Headquarter.class.getSimpleName(), new Headquarter());
	}

	
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		this.setName(Headquarter.class.getSimpleName());
		this.setSize(SIZE);
		this.setSightRange(SIGHT_RANGE);
		this.setMaxHealth(maxHealth);
		deposit = true;
	}
	
	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		Headquarter newHQ = new Headquarter();
		newHQ.initialize(owner, pos);
		return newHQ;
	}

	
}
