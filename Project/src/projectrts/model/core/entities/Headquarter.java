package projectrts.model.core.entities;

import projectrts.model.core.EntityFactory;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.AttackAbility;
import projectrts.model.core.abilities.TrainWorkerAbility;
/**
 * 
 * @author Jakob Svensson
 *
 */
public class Headquarter extends PlayerControlledEntity{
	

	private static String name = "Headquarter";
	private static float size = 2;
	
	static {
		EntityFactory.INSTANCE.registerPCE(name, new Headquarter());
	}
	
	private Headquarter(){
	}
	
	private Headquarter(Player owner, Position spawnPos){
		super(owner, spawnPos);
		this.abilities.add(new TrainWorkerAbility());
		setName(name);
		setSize(size);
	}
	
	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		return new Headquarter(owner, pos);
	}

}
