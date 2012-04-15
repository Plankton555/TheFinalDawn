package projectrts.model.core.abilities;

import java.util.HashMap;

public enum AbilityFactory {INSTANCE;

private HashMap<String, AbstractAbility> abilityMap = new HashMap<String, AbstractAbility>();

public void registerAbility(String abilityType, AbstractAbility ability) {
	abilityMap.put(abilityType, ability);
}

public AbstractAbility createAbility(String abilityType) {
	return abilityMap.get(abilityType).createAbility();
}

}
