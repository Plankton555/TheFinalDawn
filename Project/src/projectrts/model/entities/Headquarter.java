package projectrts.model.entities;

import projectrts.model.abilities.AbilityFactory;
import projectrts.model.abilities.TrainWorkerAbility;
import projectrts.model.player.IPlayer;
import projectrts.model.utils.Position;
/**
 * A building for creating workers and deposit resources
 * @author Jakob Svensson
 *
 */
public class Headquarter extends AbstractStructure{
	
	private static float size = 2;
	// TODO Jakob: PMD error, "Variables that are final and static should be all in caps"
	private static final float sightRange = 5;
	private static int maxHealth = 2000;
	
	static {
		EntityFactory.INSTANCE.registerPCE(Headquarter.class.getSimpleName(), new Headquarter());
	}

	
	protected void initialize(IPlayer owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		this.setName(Headquarter.class.getSimpleName());
		this.setSize(size);
		this.setSightRange(sightRange);
		this.setMaxHealth(maxHealth);
		deposit = true;
	}
	
	@Override
	public PlayerControlledEntity createPCE(IPlayer owner, Position pos) {
		Headquarter newHQ = new Headquarter();
		newHQ.initialize(owner, pos);
		return newHQ;
	}

	
}
