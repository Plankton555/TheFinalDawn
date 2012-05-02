package projectrts.model.abilities;

import projectrts.model.entities.EntityManager;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.utils.Position;

/**
 * An ability for attacking
 * @author Filip Brynfors
 *
 */
public class AttackAbility extends AbstractAbility implements IUsingMoveAbility, ITargetAbility {
	private IPlayerControlledEntity entity;
	private IPlayerControlledEntity target;
	
	private MoveAbility moveAbility;
	private double range = 1;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(AttackAbility.class.getSimpleName(), new AttackAbility());
	}
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(IPlayerControlledEntity entity, MoveAbility moveAbility) {
		this.entity = entity;
		this.moveAbility = moveAbility;
		this.setCooldown(0.5f);
	}
	
	@Override
	public String getName() {
		return "Attack";
	}
	
	@Override
	public void useAbility(Position pos){
		target = EntityManager.getInstance().getPCEAtPosition(pos);
		setActive(true);
		setFinished(false);
		
	}

	@Override
	public void update(float tpf) {
		updateCooldown(tpf);		
		
		
		if(isActive() && !isFinished()){
			//attacker.getRange();
			if(inRange(target)){
				//In range
				if(getRemainingCooldown()<=0){
					
					target.dealDamageTo(entity.getDamage());
					this.setAbilityUsed();
			
					if(target.getCurrentHealth() == 0) {
						this.setFinished(true);
					}
				}
			} else {
				//Out of range
				
				if(!moveAbility.isActive()){
					moveAbility.useAbility(target.getPosition());
				}
				else
				{
					moveAbility.updateTarget(target.getPosition());
				}
			}
		}
	}

	@Override
	public AbstractAbility createAbility(IPlayerControlledEntity entity, MoveAbility moveAbility) {
		AttackAbility newAbility = new AttackAbility();
		newAbility.initialize(entity, moveAbility);
		return newAbility;
	}

	private boolean inRange(IPlayerControlledEntity target)
	{
		return (Position.getDistance(entity.getPosition(), target.getPosition()) < range + (target.getSize()/2)*1.5);
	}
}
