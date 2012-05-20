package projectrts.model.abilities;

import java.util.HashMap;
import java.util.Map;

import projectrts.model.entities.AbstractPlayerControlledEntity;

/**
 * A factory for abilities.
 * 
 * @author Markus Ekstrom
 */
final class AbilityFactory {
	private static Map<String, AbstractAbility> abilityMap = new HashMap<String, AbstractAbility>();

	public static void registerAbility(String abilityType,
			AbstractAbility ability) {
		abilityMap.put(abilityType, ability);
	}

	public static AbstractAbility createAbility(String abilityType,
			AbstractPlayerControlledEntity entity) {
		AbstractAbility ability = abilityMap.get(abilityType);
		if (ability == null) {
			throw new IllegalStateException("You must register " + abilityType
					+ " before you can use it");
		} else if (!(ability instanceof IStationaryAbility)) {
			throw new IllegalStateException(abilityType
					+ " does not implement "
					+ IStationaryAbility.class.getSimpleName());
		}
		IStationaryAbility nMovableAbility = (IStationaryAbility) ability;
		return nMovableAbility.createAbility(entity);
	}

	public static AbstractAbility createMoveableAbility(String abilityType,
			AbstractPlayerControlledEntity entity, MoveAbility moveAbility) {
		AbstractAbility ability = abilityMap.get(abilityType);
		if (ability == null) {
			throw new IllegalStateException("You must register " + abilityType
					+ " before you can use it");
		} else if (!(ability instanceof IMoveable)) {
			throw new IllegalStateException(abilityType
					+ " does not implement " + IMoveable.class.getSimpleName());
		}
		IMoveable movableAbility = (IMoveable) ability;
		return movableAbility.createAbility(entity, moveAbility);
	}

	private AbilityFactory() {
	}
}