package projectrts.model.core.entities;

import projectrts.model.core.EntityFactory;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.AttackAbility;
import projectrts.model.core.abilities.GatherResourceAbility;
import projectrts.model.core.abilities.MoveAbility;

/**
 * A worker unit for building and harvesting
 * @author Jakob Svensson
 *
 */
public class Worker extends PlayerControlledEntity{
	
	private static String name = "Worker";
	private static float size = 1f;

	static {
		EntityFactory.INSTANCE.registerPCE(name, new Worker());
	}
	
	private Worker() {
	}
	
	private Worker(Player owner, Position spawnPos) {
		super(owner, spawnPos);
		this.abilities.add(new AttackAbility());
		this.abilities.add(new GatherResourceAbility());
		abilities.add(new MoveAbility());
		setName(name);
		setSize(size);
	}
	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		return new Worker(owner, pos);
	}

}
