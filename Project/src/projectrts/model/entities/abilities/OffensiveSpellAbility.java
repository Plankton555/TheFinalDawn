package projectrts.model.entities.abilities;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.utils.ModelUtils;
import projectrts.model.utils.Position;


/**
 * A basic ability for a spell
 * @author Filip Brynfors
 *
 */
public class OffensiveSpellAbility extends AbstractAbility implements IUsingMoveAbility {
	private int abilityRange = 50;
	private int damage = 90;
	
	private PlayerControlledEntity attacker;
	private PlayerControlledEntity target;
	
	private AbstractAbility moveAbility;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(OffensiveSpellAbility.class.getSimpleName(), new OffensiveSpellAbility());
	}
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(PlayerControlledEntity entity, MoveAbility moveAbility) {
		this.attacker = entity;
		this.moveAbility = moveAbility;
		this.setCooldown(5);
	}

	@Override
	public String getName() {
		return "Offensive Spell";
	}
	

	public void useAbility(Position pos){
		target = EntityManager.getInstance().getPCEAtPosition(pos);
	}

	@Override
	public void update(float tpf) {
		updateCooldown(tpf);		
		
			if(isActive() && !isFinished()){
			
			//attacker.getRange();
			if(Position.getDistance(attacker.getPosition(), target.getPosition())>abilityRange){
				//Out of range
				
				if(!moveAbility.isActive()){
					moveAbility.useAbility(target.getPosition());
				}
				
				if(moveAbility.isFinished()){
					moveAbility.setActive(true);
					moveAbility.setFinished(false);
				}
				
			} else {
				//In range			
				
				if(this.getRemainingCooldown()<=0){
					target.dealDamageTo(damage);
					
					this.setAbilityUsed();
					this.setFinished(true);
				
				}
			}

		}
	}

	@Override
	public AbstractAbility createAbility(PlayerControlledEntity entity, MoveAbility moveAbility) {
		OffensiveSpellAbility newAbility = new OffensiveSpellAbility();
		newAbility.initialize(entity, moveAbility);
		return newAbility;
	}
}