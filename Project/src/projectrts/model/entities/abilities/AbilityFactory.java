package projectrts.model.entities.abilities;

import java.util.HashMap;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.PlayerControlledEntity;

//TODO Markus: ADD JAVADOC!!
public enum AbilityFactory {INSTANCE;

	private HashMap<String, AbstractAbility> abilityMap = new HashMap<String, AbstractAbility>();
	
	public void registerAbility(String abilityType, AbstractAbility ability) {
		abilityMap.put(abilityType, ability);
	}
	
	public AbstractAbility createAbility(String abilityType, PlayerControlledEntity entity) {
		AbstractAbility ability = abilityMap.get(abilityType);
		if (ability != null && ability instanceof INonMovableAbility)
		{
			INonMovableAbility nMovableAbility = (INonMovableAbility) ability;
			return nMovableAbility.createAbility(entity);
		}
		else
		{
			// TODO Anyone: Throw exception instead of returning null?
			return null;
		}
	}
	
	public AbstractAbility createMAbility(String abilityType, PlayerControlledEntity entity, MoveAbility moveAbility) {
		AbstractAbility ability = abilityMap.get(abilityType);
		if (ability != null && ability instanceof IMovableAbility)
		{
			IMovableAbility movableAbility = (IMovableAbility) ability;
			return movableAbility.createAbility(entity, moveAbility);
		}
		else
		{
			// TODO Anyone: Throw exception instead of returning null?
			return null;
		}
	}
}
