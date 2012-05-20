package projectrts.model.entities;

import java.util.HashMap;
import java.util.Map;

import projectrts.model.world.Position;

/**
 * A factory for entities.
 * 
 * @author Markus Ekström
 * 
 */
public final class EntityFactory {
	private static Map<String, AbstractPlayerControlledEntity> pceMap = new HashMap<String, AbstractPlayerControlledEntity>();
	private static Map<String, AbstractNonPlayerControlledEntity> npceMap = new HashMap<String, AbstractNonPlayerControlledEntity>();

	/**
	 * Registers a PlayerControlledEntity in the factory. Registering an entity
	 * enables creation of that entity through createPCE.
	 * 
	 * @param pceType
	 *            The class name of the entity (use class.getSimpleName()).
	 * @param pce
	 *            An instance of the entity you want to register.
	 */
	public static void registerPCE(String pceType,
			AbstractPlayerControlledEntity pce) {
		pceMap.put(pceType, pce);
	}

	/**
	 * Creates a PlayerControlledEntity according to specifications. If the
	 * entity's class hasn't been registered then it returns null.
	 * 
	 * @param pceType
	 *            The class name of the desired entity (use
	 *            class.getSimpleName()).
	 * @param aiPlayer
	 *            The owner of the desired entity.
	 * @param pos
	 *            The position of the desired entity.
	 * @return An instance of the desired entity.
	 */
	public static AbstractPlayerControlledEntity createPCE(String pceType,
			Player aiPlayer, Position pos) {
		if (pceMap.get(pceType) == null) {
			throw new IllegalStateException("You must register " + pceType
					+ " before you can use it");
		}

		return pceMap.get(pceType).createPCE(aiPlayer, pos);
	}

	/**
	 * Registers a NonPlayerControlledEntity in the factory. Registering an
	 * entity enables creation of that entity through createNPCE.
	 * 
	 * @param npceType
	 *            The class name of the entity (use class.getSimpleName()).
	 * @param npce
	 *            An instance of the entity you want to register.
	 */
	public static void registerNPCE(String npceType,
			AbstractNonPlayerControlledEntity npce) {
		npceMap.put(npceType, npce);
	}

	/**
	 * Creates a NonPlayerControlledEntity according to specifications. If the
	 * entity's class hasn't been registered then it returns null.
	 * 
	 * @param npceType
	 *            The class name of the desired entity (use
	 *            class.getSimpleName()).
	 * @param pos
	 *            The position of the desired entity.
	 * @return An instance of the desired entity.
	 */
	public static AbstractNonPlayerControlledEntity createNPCE(String npceType,
			Position pos) {
		if (npceMap.get(npceType) == null) {
			throw new IllegalStateException("You must register " + npceType
					+ " before you can use it");
		}
		return npceMap.get(npceType).createNPCE(pos);
	}

	private EntityFactory() {
	}
}