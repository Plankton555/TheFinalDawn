package projectrts.model.entities;

import projectrts.model.world.Position;

/**
 * An example of a concrete unit and it's methods.
 * 
 * @author Markus Ekström
 * 
 */
public class Warrior extends AbstractUnit {

	private static final float SIZE = 1f;
	private static final float SPEED = 4f;
	private static final float SIGHT_RANGE = 5f;
	private static final float ATTACK_RANGE = 1f;
	private static final int DAMAGE = 20;
	private static final int MAX_HEALTH = 100;

	static {
		EntityFactory.registerPCE(Warrior.class.getSimpleName(), new Warrior());
	}

	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		this.setName(Warrior.class.getSimpleName());
		this.setSightRange(SIGHT_RANGE);
		this.setAttackRange(ATTACK_RANGE);
		this.setMaxHealth(MAX_HEALTH);
		this.setSize(SIZE);
		this.setSpeed(SPEED);
		this.setDamage(DAMAGE);
	}

	@Override
	public AbstractPlayerControlledEntity createPCE(Player owner, Position pos) {
		Warrior newWarrior = new Warrior();
		newWarrior.initialize(owner, pos);
		return newWarrior;
	}
}
