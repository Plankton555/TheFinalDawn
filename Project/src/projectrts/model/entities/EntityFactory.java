package projectrts.model.entities;

import java.util.HashMap;

import projectrts.model.player.Player;
import projectrts.model.utils.Position;

/**
 * 
 * @author Markus Ekström
 *
 */
public enum EntityFactory {INSTANCE;
	
	private HashMap<String, PlayerControlledEntity> pceMap = new HashMap<String, PlayerControlledEntity>();
	private HashMap<String, NonPlayerControlledEntity> npceMap = new HashMap<String, NonPlayerControlledEntity>();

	public void registerPCE(String pceType, PlayerControlledEntity pce) {
		pceMap.put(pceType, pce);
	}

	// TODO Markus: Add javadoc
	public PlayerControlledEntity createPCE(String pceType, Player owner, Position pos) {
		return pceMap.get(pceType).createPCE(owner, pos);
	}
	
	public void registerNPCE(String npceType, NonPlayerControlledEntity npce) {
		npceMap.put(npceType, npce);
	}
	
	public NonPlayerControlledEntity createNPCE(String npceType, Position pos) {
		return npceMap.get(npceType).createNPCE(pos);
	}
	

}
