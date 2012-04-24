package projectrts.model.entities.structures;

import projectrts.model.entities.AbstractStructure;
import projectrts.model.entities.EntityFactory;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.abilities.AbilityFactory;
import projectrts.model.entities.abilities.TrainWorkerAbility;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;
/**
 * 
 * @author Jakob Svensson
 *
 */
public class Headquarter extends AbstractStructure{
	
	private static float size = 2;
	
	static {
		EntityFactory.INSTANCE.registerPCE(Headquarter.class.getSimpleName(), new Headquarter());
	}

	
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		this.abilities.add(AbilityFactory.INSTANCE.createAbility(TrainWorkerAbility.class.getSimpleName(), this));
		setName(Headquarter.class.getSimpleName());
		setSize(size);
		deposit = true;
	}
	
	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		Headquarter newHQ = new Headquarter();
		newHQ.initialize(owner, pos);
		return newHQ;
	}

	@Override
	public float getSightRange() {
		return 5;
	}
}
