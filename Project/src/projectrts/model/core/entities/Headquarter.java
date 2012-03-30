package projectrts.model.core.entities;

import projectrts.model.core.EntityFactory;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
/**
 * 
 * @author Jakob Svensson
 *
 */
public class Headquarter extends PlayerControlledEntity{
	

	private static String name = "Headquarter";
	
	static {
		EntityFactory.INSTANCE.registerPCE(name, new Headquarter());
	}
	
	private Headquarter(){
	}
	
	private Headquarter(Player owner, Position spawnPos){
		super(owner, spawnPos);
		//TODO: Add abilities to train units
		setName(name);
	}
	
	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		return new Headquarter(owner, pos);
	}

}
