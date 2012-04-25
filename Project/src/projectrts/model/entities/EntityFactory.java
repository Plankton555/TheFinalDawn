package projectrts.model.entities;

import java.util.HashMap;

import projectrts.model.player.Player;
import projectrts.model.utils.Position;

/**
 * A factory for entities.
 * @author Markus Ekström
 *
 */
public enum EntityFactory {INSTANCE;
	
	private HashMap<String, PlayerControlledEntity> pceMap = new HashMap<String, PlayerControlledEntity>();
	private HashMap<String, NonPlayerControlledEntity> npceMap = new HashMap<String, NonPlayerControlledEntity>();

	/**
	 * Registers a PlayerControlledEntity in the factory. Registering an entity enables
	 * creation of that entity through createPCE.
	 * @param pceType The class name of the entity (use class.getSimpleName()).
	 * @param pce An instance of the entity you want to register.
	 */
	public void registerPCE(String pceType, PlayerControlledEntity pce) {
		pceMap.put(pceType, pce);
	}

	/**
	 * Creates a PlayerControlledEntity according to specifications. If the entity's class hasn't been
	 * registered then it returns null.
	 * @param pceType The class name of the desired entity (use class.getSimpleName()).
	 * @param owner The owner of the desired entity.
	 * @param pos The position of the desired entity.
	 * @return An instance of the desired entity.
	 */
	public PlayerControlledEntity createPCE(String pceType, Player owner, Position pos) {
		if(pceMap.get(pceType) == null) {
			return null;
		}
		
		return pceMap.get(pceType).createPCE(owner, pos);
	}
	
	/**
	 * Registers a NonPlayerControlledEntity in the factory. Registering an entity enables
	 * creation of that entity through createNPCE.
	 * @param npceType The class name of the entity (use class.getSimpleName()).
	 * @param npce An instance of the entity you want to register.
	 */
	public void registerNPCE(String npceType, NonPlayerControlledEntity npce) {
		npceMap.put(npceType, npce);
	}
	
	/**
	 * Creates a NonPlayerControlledEntity according to specifications. If the entity's class hasn't been
	 * registered then it returns null.
	 * @param npceType The class name of the desired entity (use class.getSimpleName()).
	 * @param pos The position of the desired entity.
	 * @return An instance of the desired entity.
	 */
	public NonPlayerControlledEntity createNPCE(String npceType, Position pos) {
		if(pceMap.get(npceType) == null) {
			return null;
		}
		return npceMap.get(npceType).createNPCE(pos);
	}
	

}
