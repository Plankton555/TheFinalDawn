package projectrts.model.entities;

import projectrts.model.world.Position;

/**
 *  An example of a concrete unit and it's methods.
 * @author Markus Ekström
 *
 */
public class Warrior extends AbstractUnit{
	
	// TODO Jakob: PMD error, "Variables that are final and static should be all in caps"
	private static final float size = 1f;
	private static final float speed = 4f;
	private static final float sightRange = 5f;
	private static final int damage = 20;
	private static final int maxHealth = 100;
	
	
	static {
		EntityFactory.INSTANCE.registerPCE(Warrior.class.getSimpleName(), new Warrior());
	}
	
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		setName(Warrior.class.getSimpleName());
		setSightRange(sightRange);
		this.setMaxHealth(maxHealth);
		this.setSize(size);
		this.setSpeed(speed);
		this.setDamage(damage);
	}

	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		Warrior newWarrior = new Warrior();
		newWarrior.initialize(owner, pos);
		return newWarrior;
	}

}
