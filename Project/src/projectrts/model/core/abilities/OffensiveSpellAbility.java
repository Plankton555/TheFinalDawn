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
	
	private PlayerControlledEntity attacker;
	private PlayerControlledEntity target;
	
	private AbstractAbility moveAbility;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(OffensiveSpellAbility.class.getSimpleName(), new OffensiveSpellAbility());
	}
	
	private OffensiveSpellAbility() {
		super(5);
	}

	@Override
	public String getName() {
		return "Offensive Spell";
	}
	

	public void useAbility(PlayerControlledEntity attacker, Position pos){
		this.attacker = attacker;
		target = ModelUtils.INSTANCE.getPCEAtPosition(pos);
	}

	@Override
	public void update(float tpf) {
		updateCooldown(tpf);		
		
			if(isActive() && !isFinished()){
			
			//attacker.getRange();
			if(ModelUtils.INSTANCE.getDistance(attacker.getPosition(), target.getPosition())>abilityRange){
				//Out of range
				
				if(!moveAbility.isActive()){
					moveAbility.useAbility(attacker, target.getPosition());
				}
				
				moveAbility.update(tpf);
				if(moveAbility.isFinished()){
					moveAbility.setActive(true);
					moveAbility.setFinished(false);
				}
				
			} else {
				//In range			
				
				if(this.getRemainingCooldown()<=0){
					target.adjustHealth(-damage);
					
					this.setAbilityUsed();
					this.setFinished(true);
				
				}
			}

		}
	}

	@Override
	public AbstractAbility createAbility() {
		OffensiveSpellAbility newAbility = new OffensiveSpellAbility();
		newAbility.moveAbility = AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName());
		return newAbility;
	}
}