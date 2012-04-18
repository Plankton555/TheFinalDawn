package projectrts.model.core.entities;

import projectrts.model.core.EntityFactory;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.AbilityFactory;
import projectrts.model.core.abilities.AttackAbility;
import projectrts.model.core.abilities.GatherResourceAbility;
import projectrts.model.core.abilities.MoveAbility;

/**
 * A worker unit for building and harvesting
 * @author Jakob Svensson
 *
 */
public class Worker extends PlayerControlledEntity{
	
	private static float size = 1f;
	private static float speed = 5;

	static {
		EntityFactory.INSTANCE.registerPCE(Worker.class.getSimpleName(), new Worker());
	}
	
	private Worker() {
	}
	
	private Worker(Player owner, Position spawnPos) {
		super(owner, spawnPos);
		this.abilities.add(AbilityFactory.INSTANCE.createAbility(AttackAbility.class.getSimpleName()));
		this.abilities.add(AbilityFactory.INSTANCE.createAbility(GatherResourceAbility.class.getSimpleName()));
		abilities.add(AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName()));
		setName(Worker.class.getSimpleName());
		setSize(size);
		setSpeed(speed);
	}
	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		return new Worker(owner, pos);
	}

	@Override
	public float getSightRange() {
		return 5;
	}

	@Override
	public int getDamage() {
		return 5;
	}

}
