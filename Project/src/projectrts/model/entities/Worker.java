package projectrts.model.entities;

import projectrts.model.world.Position;

/**
 * A worker unit for building and harvesting
 * @author Jakob Svensson
 *
 */
public class Worker extends AbstractUnit{
	
	private static final float SIZE = 1f;
	private static final float SPEED = 5;
	private static final int DAMAGE = 5;
	private static final float SIGHT_RANGE = 5;
	private static final int MAXH_HEALTH = 50;

	static {
		EntityFactory.INSTANCE.registerPCE(Worker.class.getSimpleName(), new Worker());
	}
	
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		setName(Worker.class.getSimpleName());
		setSize(SIZE);
		setSpeed(SPEED);
		setMaxHealth(MAXH_HEALTH);
		setSightRange(SIGHT_RANGE);
		this.setDamage(DAMAGE);
	}
	
	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		Worker newWorker = new Worker();
		newWorker.initialize(owner, pos);
		return newWorker;
	}
}
