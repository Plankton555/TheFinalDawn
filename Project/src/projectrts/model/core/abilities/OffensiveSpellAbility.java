package projectrts.model.core.abilities;

import projectrts.model.core.entities.IPlayerControlledEntity;
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
	

	public void useAbility(IPlayerControlledEntity attacker, IPlayerControlledEntity target){
		
		
		if(this.getRemainingCooldown()<=0){
			if(ModelUtils.INSTANCE.getDistance(attacker.getPosition(), target.getPosition()) <= abilityRange 
					&& target instanceof PlayerControlledEntity){
				PlayerControlledEntity entityTarget = (PlayerControlledEntity) target;
				entityTarget.takeDamage(damage);
				
				this.setAbilityUsed();
			}
		}
	}

}