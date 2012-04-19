package projectrts.model.entities.abilities;

import java.util.HashMap;

import projectrts.model.entities.AbstractAbility;

public enum AbilityFactory {INSTANCE;

private HashMap<String, AbstractAbility> abilityMap = new HashMap<String, AbstractAbility>();

public void registerAbility(String abilityType, AbstractAbility ability) {
	abilityMap.put(abilityType, ability);
}

public AbstractAbility createAbility(String abilityType) {
	return abilityMap.get(abilityType).createAbility();
}

}
