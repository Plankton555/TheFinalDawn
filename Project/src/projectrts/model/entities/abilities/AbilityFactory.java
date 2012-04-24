package projectrts.model.entities.abilities;

import java.util.HashMap;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.PlayerControlledEntity;

//TODO Markus(?): ADD JAVADOC!!
public enum AbilityFactory {INSTANCE;

private HashMap<String, AbstractAbility> abilityMap = new HashMap<String, AbstractAbility>();

public void registerAbility(String abilityType, AbstractAbility ability) {
	abilityMap.put(abilityType, ability);
}

public AbstractAbility createAbility(String abilityType, PlayerControlledEntity entity) {
	return abilityMap.get(abilityType).createAbility(entity);
}

}
