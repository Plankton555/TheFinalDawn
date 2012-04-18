package projectrts.model.core.entities;

import projectrts.model.core.EntityFactory;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.AbilityFactory;
import projectrts.model.core.abilities.TrainWorkerAbility;
/**
 * 
 * @author Jakob Svensson
 *
 */
public class Headquarter extends PlayerControlledEntity{
	
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
