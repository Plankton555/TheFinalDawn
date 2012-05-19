package projectrts.model.abilities;

import java.util.HashMap;
import java.util.Map;

import projectrts.model.entities.PlayerControlledEntity;

//TODO Markus: ADD JAVADOC!!
// TODO Markus: PMD: A class which only has private constructors should be final
class AbilityFactory {
	private static Map<String, AbstractAbility> abilityMap = new HashMap<String, AbstractAbility>();
	
	// TODO Markus: Add javadoc
	public static void registerAbility(String abilityType, AbstractAbility ability) {
		abilityMap.put(abilityType, ability);
	}
	
	// TODO Markus: Add javadoc
	public static AbstractAbility createAbility(String abilityType, PlayerControlledEntity entity) {
		AbstractAbility ability = abilityMap.get(abilityType);
		if (ability == null)
		{
			throw new IllegalStateException("You must register "+ abilityType +
					" before you can use it");
		}
		else if (!(ability instanceof INotUsingMoveAbility))
		{
			throw new IllegalStateException(abilityType +
					" does not implement " + INotUsingMoveAbility.class.getSimpleName());
		}
		INotUsingMoveAbility nMovableAbility = (INotUsingMoveAbility) ability;
		return nMovableAbility.createAbility(entity);
	}
	
	// TODO Markus: Add javadoc
	public static AbstractAbility createUsingMoveAbility(String abilityType, PlayerControlledEntity entity, MoveAbility moveAbility) {
		AbstractAbility ability = abilityMap.get(abilityType);
		if (ability == null)
		{
			throw new IllegalStateException("You must register "+ abilityType +
					" before you can use it");
		}
		else if (!(ability instanceof IUsingMoveAbility))
		{
			throw new IllegalStateException(abilityType +
					" does not implement " + IUsingMoveAbility.class.getSimpleName());
		}
		IUsingMoveAbility movableAbility = (IUsingMoveAbility) ability;
		return movableAbility.createAbility(entity, moveAbility);
	}
	
	private AbilityFactory(){}
}
