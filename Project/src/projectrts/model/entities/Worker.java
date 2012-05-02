package projectrts.model.entities;

import projectrts.model.player.IPlayer;
import projectrts.model.utils.Position;

/**
 * A worker unit for building and harvesting
 * @author Jakob Svensson
 *
 */
public class Worker extends AbstractUnit{
	
	// TODO Jakob: PMD error, "Variables that are final and static should be all in caps"
	private static final float size = 1f;
	private static final float speed = 5;
	private static final int damage = 5;
	private static final float sightRange = 5;
	private static final int maxHealth = 50;

	static {
		EntityFactory.INSTANCE.registerPCE(Worker.class.getSimpleName(), new Worker());
	}
	
	protected void initialize(IPlayer owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		setName(Worker.class.getSimpleName());
		setSize(size);
		setSpeed(speed);
		setMaxHealth(maxHealth);
		setSightRange(sightRange);
		this.setDamage(damage);
	}
	
	@Override
	public PlayerControlledEntity createPCE(IPlayer owner, Position pos) {
		Worker newWorker = new Worker();
		newWorker.initialize(owner, pos);
		return newWorker;
	}
}
