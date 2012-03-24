package projectrts.model.core.abilities;

import projectrts.model.core.entities.IPlayerControlledEntity;
import projectrts.model.core.entities.PlayerControlledEntity;

/**
 * An ability for attacking
 * @author Filip Brynfors
 *
 */
public class AttackAbility implements IAbility {

	@Override
	public String getName() {
		return "Attack";
	}
	
	public void doAbility(IPlayerControlledEntity attacker, IPlayerControlledEntity target){
		
		//TODO: Implement functionality for this ability
		//Check if possible? Should it be check multiple times (Here + in AI)?
		//The amount of dmg should be attacker.getDamage()
		
		if(target instanceof PlayerControlledEntity){
			PlayerControlledEntity entityTarget = (PlayerControlledEntity) target;
			entityTarget.takeDamage(50);
		}
	}
	
	
}
