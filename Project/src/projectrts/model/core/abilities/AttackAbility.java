package projectrts.model.core.abilities;

import projectrts.model.core.Position;
import projectrts.model.core.entities.PlayerControlledEntity;
import projectrts.model.core.utils.ModelUtils;

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
	
	@Override
	public void useAbility(PlayerControlledEntity attacker, Position pos){
		PlayerControlledEntity target = ModelUtils.INSTANCE.getPlayerControlledEntityAtPosition(pos);
		
		//TODO: The amount of dmg should be attacker.getDamage()
		
		if(target != null){
			target.takeDamage(50);
			
			this.setAbilityUsed();
		}
	}

}
