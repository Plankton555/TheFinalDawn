package projectrts.model.entities;

import projectrts.model.world.Position;

/**
 * A ranged attacking unit.
 * 
 * @author Jakob Svensson
 * 
 */
public class Ranged extends AbstractUnit {

	private static final float SIZE = 1f;
	private static final float SPEED = 4f;
	private static final float SIGHT_RANGE = 6f;
	private static final float ATTACK_RANGE = 4f;
	private static final int DAMAGE = 15;
	private static final int MAX_HEALTH = 80;

	static {
		EntityFactory.registerPCE(Ranged.class.getSimpleName(), new Ranged());
	}

	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		this.setName(Ranged.class.getSimpleName());
		this.setSightRange(SIGHT_RANGE);
		this.setAttackRange(ATTACK_RANGE);
		this.setMaxHealth(MAX_HEALTH);
		this.setSize(SIZE);
		this.setSpeed(SPEED);
		this.setDamage(DAMAGE);
	}

	@Override
	public AbstractPlayerControlledEntity createPCE(Player owner, Position pos) {
		Ranged newWarrior = new Ranged();
		newWarrior.initialize(owner, pos);
		return newWarrior;
	}
}
