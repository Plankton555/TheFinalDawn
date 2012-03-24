package projectrts.model.core.abilities;

import projectrts.model.core.entities.IPlayerControlledEntity;
import projectrts.model.core.entities.PlayerControlledEntity;


/**
 * A basic ability for a spell
 * @author Filip Brynfors
 *
 */
public class OffensiveSpellAbility implements IAbility {
	private int abilityRange = 50;
	private int damage = 90;

	@Override
	public String getName() {
		return "Offensive Spell";
	}
	
	@Override
	public float getCooldown() {
		return 5;
	}
	
	public void doAbility(IPlayerControlledEntity attacker, IPlayerControlledEntity target){
		
		//TODO: Implement functionality for this ability
		//Check if possible? Should it be check multiple times (Here + in AI)?
		//The amount of dmg should be attacker.getDamage()
		float dx = attacker.getPosition().getX() - target.getPosition().getX();
		float dy = attacker.getPosition().getY() - target.getPosition().getY();
		
		float distance = (float) Math.sqrt(dx*dx+dy*dy);
		
		
		if(distance <= abilityRange && target instanceof PlayerControlledEntity){
			PlayerControlledEntity entityTarget = (PlayerControlledEntity) target;
			entityTarget.takeDamage(damage);
		}
	}

}