package projectrts.model.entities;

import projectrts.model.world.Position;

/**
 * A worker unit for building and harvesting
 * 
 * @author Jakob Svensson
 * 
 */
public class Worker extends AbstractUnit {

	private static final float SIZE = 1f;
	private static final float SPEED = 5;
	private static final int DAMAGE = 5;
	private static final float SIGHT_RANGE = 5;
	private static final float ATTACK_RANGE = 1f;
	private static final int MAXH_HEALTH = 50;

	static {
		EntityFactory.registerPCE(Worker.class.getSimpleName(), new Worker());
	}

	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		this.setName(Worker.class.getSimpleName());
		this.setSize(SIZE);
		this.setSpeed(SPEED);
		this.setSightRange(SIGHT_RANGE);
		this.setMaxHealth(MAXH_HEALTH);
		this.setAttackRange(ATTACK_RANGE);
		this.setDamage(DAMAGE);
	}

	@Override
	public AbstractPlayerControlledEntity createPCE(Player owner, Position pos) {
		Worker newWorker = new Worker();
		newWorker.initialize(owner, pos);
		return newWorker;
	}
}
