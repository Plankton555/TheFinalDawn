package projectrts.model.core.abilities;

import projectrts.model.core.entities.IPlayerControlledEntity;
import projectrts.model.core.entities.PlayerControlledEntity;

/**
 * An ability for attacking
 * @author Filip Brynfors
 *
 */
public class AttackAbility extends AbstractAbility {

	public AttackAbility(){
		super(1);
	}
	
	@Override
	public String getName() {
		return "Attack";
	}
	
	public void doAbility(IPlayerControlledEntity attacker, IPlayerControlledEntity target){
		
		
		//TODO: The amount of dmg should be attacker.getDamage()
		
		if(target instanceof PlayerControlledEntity){
			PlayerControlledEntity entityTarget = (PlayerControlledEntity) target;
			entityTarget.takeDamage(50);
			
			this.setAbilityUsed();
		}
	}

}
