package projectrts.model.entities.abilities;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.utils.ModelUtils;
import projectrts.model.utils.Position;

/**
 * An ability for attacking
 * @author Filip Brynfors
 *
 */
public class AttackAbility extends AbstractAbility {
	private PlayerControlledEntity attacker;
	private PlayerControlledEntity target;
	
	private AbstractAbility moveAbility;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(AttackAbility.class.getSimpleName(), new AttackAbility());
	}
	
	private AttackAbility(){
		super(1);
	}
	
	@Override
	public String getName() {
		return "Attack";
	}
	
	@Override
	public void useAbility(PlayerControlledEntity attacker, Position pos){
		this.attacker = attacker;
		target = EntityManager.getInstance().getPCEAtPosition(pos);
		setActive(true);
		setFinished(false);
		
	}

	@Override
	public void update(float tpf) {
		updateCooldown(tpf);		
		
		
		if(isActive() && !isFinished()){
			
			//attacker.getRange();
			if(ModelUtils.INSTANCE.getDistance(attacker.getPosition(), target.getPosition())>1){
				//Out of range
				
				if(!moveAbility.isActive()){
					moveAbility.useAbility(attacker, target.getPosition());
				}
				
				moveAbility.update(tpf);
				if(moveAbility.isFinished()){
					moveAbility.setActive(false);
					moveAbility.setFinished(true);
				}
				
				
			} else {
				//In range
				if(getRemainingCooldown()<=0){
					
					target.adjustHealth(-attacker.getDamage());
					this.setAbilityUsed();
			
					if(target.getCurrentHealth() == 0) {
						this.setActive(false);
						this.setFinished(true);
					}
					
				}
			}
		}
	}

	@Override
	public AbstractAbility createAbility() {
		AttackAbility newAbility = new AttackAbility();
		newAbility.moveAbility = AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName());
		return newAbility;
	}

}
