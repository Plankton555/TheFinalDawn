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
		setSightRange(5);
	}
	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		return new Worker(owner, pos);
	}

	@Override
	public int getDamage() {
		return 5;
	}

}
