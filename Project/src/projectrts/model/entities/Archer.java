package projectrts.model.entities;

import projectrts.model.world.Position;

/**
 *  A ranged attacking unit.
 * @author Jakob Svensson
 *
 */
public class Archer extends AbstractUnit{
	
	private static final float SIZE = 1f;
	private static final float SPEED = 4f;
	private static final float SIGHT_RANGE = 6f;
	private static final float ATTACK_RANGE = 4f;
	private static final int DAMAGE = 15;
	private static final int MAX_HEALTH = 80;
	
	
	static {
		EntityFactory.INSTANCE.registerPCE(Archer.class.getSimpleName(), new Archer());
	}
	
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		this.setName(Archer.class.getSimpleName());
		this.setSightRange(SIGHT_RANGE);
		this.setAttackRange(ATTACK_RANGE);
		this.setMaxHealth(MAX_HEALTH);
		this.setSize(SIZE);
		this.setSpeed(SPEED);
		this.setDamage(DAMAGE);
	}

	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		Archer newWarrior = new Archer();
		newWarrior.initialize(owner, pos);
		return newWarrior;
	}

}
