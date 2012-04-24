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
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize() {
		this.moveAbility = AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName());
		this.setCooldown(0.5f);
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
					
					target.dealDamageTo(attacker.getDamage());
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
		newAbility.initialize();
		return newAbility;
	}

}
