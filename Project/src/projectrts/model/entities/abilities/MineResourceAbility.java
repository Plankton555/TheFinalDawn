package projectrts.model.entities.abilities;

import projectrts.model.constants.P;
import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.misc.Resource;
import projectrts.model.utils.ModelUtils;
import projectrts.model.utils.Position;
/**
 * An ability for moving to a resource and mine from it
 * @author Jakob Svensson
 *
 */
public class MineResourceAbility extends AbstractAbility{
	private Resource targetResource;
	private PlayerControlledEntity unit;
	private AbstractAbility moveAbility;
	private int resourceCarriedAmount;
	private final float recoveryTime = 0.3f;
	private float miningCooldown = 0;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(MineResourceAbility.class.getSimpleName(), new MineResourceAbility());
	}
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize() {
		this.moveAbility = AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName());
	}
	
	@Override
	public String getName() {
		return "FetchResource";
	}

	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			
			if(ModelUtils.INSTANCE.getDistance(unit.getPosition(),targetResource.getPosition() )<1.5*targetResource.getSize()){
				//If in range of resource
				//Check cooldown and mine resource or reduce cooldown as appropriate.
				moveAbility.setFinished(true);
				if (miningCooldown <= 0) { 
					resourceCarriedAmount += targetResource.mine();
					miningCooldown = recoveryTime;
				} else {
					miningCooldown -= tpf; 
				}
				
				if(resourceCarriedAmount >= P.INSTANCE.getWorkerCarryAmount()) {
					setFinished(true);
				}
				
			}else{
				// Not in range
				
				if(!moveAbility.isActive()){
					moveAbility.useAbility(unit, targetResource.getPosition());
				}
				
				moveAbility.update(tpf);
			}
		}
		
	}

	@Override
	public void useAbility(PlayerControlledEntity entity, Position target) {
		this.unit = entity;
		this.targetResource = (Resource) EntityManager.getInstance().getNonPlayerControlledEntity(target);
		resourceCarriedAmount = 0;
		setActive(true);
		setFinished(false);
	}
	
	@Override
	public AbstractAbility createAbility() {
		MineResourceAbility newAbility = new MineResourceAbility();
		newAbility.initialize();
		return newAbility;
	}

}
