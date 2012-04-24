package projectrts.model.entities.units;

import projectrts.model.entities.AbstractUnit;
import projectrts.model.entities.EntityFactory;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.abilities.AbilityFactory;
import projectrts.model.entities.abilities.AttackAbility;
import projectrts.model.entities.abilities.GatherResourceAbility;
import projectrts.model.entities.abilities.MoveAbility;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;

/**
 * A worker unit for building and harvesting
 * @author Jakob Svensson
 *
 */
public class Worker extends AbstractUnit{
	
	private static final float size = 1f;
	private static final float speed = 5;
	private static final int damage = 5;
	private static final float sightRange = 5;
	private static final int maxHealth = 50;

	static {
		EntityFactory.INSTANCE.registerPCE(Worker.class.getSimpleName(), new Worker());
	}
	
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		this.abilities.add(AbilityFactory.INSTANCE.createAbility(AttackAbility.class.getSimpleName(), this));
		this.abilities.add(AbilityFactory.INSTANCE.createAbility(GatherResourceAbility.class.getSimpleName(), this));
		abilities.add(AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName(), this));
		setName(Worker.class.getSimpleName());
		setSize(size);
		setSpeed(speed);
		setMaxHealth(maxHealth);
		setSightRange(sightRange);
		this.setDamage(damage);
	}
	
	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		Worker newWorker = new Worker();
		newWorker.initialize(owner, pos);
		return newWorker;
	}
}
