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
	
	private Headquarter(){
	}
	
	private Headquarter(Player owner, Position spawnPos){
		super(owner, spawnPos);
		this.abilities.add(AbilityFactory.INSTANCE.createAbility(TrainWorkerAbility.class.getSimpleName()));
		setName(Headquarter.class.getSimpleName());
		setSize(size);
		deposit = true;
	}
	
	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		return new Headquarter(owner, pos);
	}

	@Override
	public float getSightRange() {
		return 5;
	}

	@Override
	public int getDamage() {
		return 0;
	}

}
