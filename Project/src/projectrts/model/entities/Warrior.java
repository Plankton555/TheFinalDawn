package projectrts.model.entities;

import projectrts.model.world.Position;

/**
 *  An example of a concrete unit and it's methods.
 * @author Markus Ekström
 *
 */
public class Warrior extends AbstractUnit{
	
	private static final float SIZE = 1f;
	private static final float SPEED = 4f;
	private static final float SIGHT_RANGE = 5f;
	private static final int DAMAGE = 20;
	private static final int MAX_HEALTH = 100;
	
	
	static {
		EntityFactory.INSTANCE.registerPCE(Warrior.class.getSimpleName(), new Warrior());
	}
	
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		setName(Warrior.class.getSimpleName());
		setSightRange(SIGHT_RANGE);
		this.setMaxHealth(MAX_HEALTH);
		this.setSize(SIZE);
		this.setSpeed(SPEED);
		this.setDamage(DAMAGE);
	}

	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		Warrior newWarrior = new Warrior();
		newWarrior.initialize(owner, pos);
		return newWarrior;
	}

}
