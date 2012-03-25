package projectrts.model.core.abilities;

import projectrts.model.core.Position;
import projectrts.model.core.entities.PlayerControlledEntity;
import projectrts.model.core.utils.ModelUtils;


/**
 * A basic ability for a spell
 * @author Filip Brynfors
 *
 */
public class OffensiveSpellAbility extends AbstractAbility {
	private int abilityRange = 50;
	private int damage = 90;
	
	public OffensiveSpellAbility() {
		super(5);
	}

	@Override
	public String getName() {
		return "Offensive Spell";
	}
	

	public void useAbility(PlayerControlledEntity attacker, Position pos){
		PlayerControlledEntity target = ModelUtils.INSTANCE.getPlayerControlledEntityAtPosition(pos);
		
		if(target != null && this.getRemainingCooldown()<=0){
			if(ModelUtils.INSTANCE.getDistance(attacker.getPosition(), target.getPosition()) <= abilityRange){
				target.takeDamage(damage);
				
				this.setAbilityUsed();
			}
		}
	}

}