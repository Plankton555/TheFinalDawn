package projectrts.model.entities.structures;

import projectrts.model.entities.AbstractStructure;
import projectrts.model.entities.EntityFactory;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.abilities.AbilityFactory;
import projectrts.model.entities.abilities.TrainWarriorAbility;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;

/**
 * A building for training warriors 
 * @author Jakob Svensson
 *
 */
public class Barracks extends AbstractStructure{
	
private static float size = 2;
private static final float sightRange = 5;
	
	static {
		EntityFactory.INSTANCE.registerPCE(Barracks.class.getSimpleName(), new Barracks());
	}
	
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		this.abilities.add(AbilityFactory.INSTANCE.createAbility(TrainWarriorAbility.class.getSimpleName(),this));
		setName(Barracks.class.getSimpleName());
		setSize(size);
		setSightRange(sightRange);
		deposit = false;
	}
	
	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		Barracks newBarracks = new Barracks();
		newBarracks.initialize(owner, pos);
		return newBarracks;
	}

}
