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
public class MineResourceAbility extends AbstractAbility implements IMovableAbility {
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
	protected void initialize(PlayerControlledEntity entity) {
		this.unit = entity;
		this.moveAbility = AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName(), entity);
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
					moveAbility.useAbility(targetResource.getPosition());
				}
				
				moveAbility.update(tpf);
			}
		}
		
	}

	@Override
	public void useAbility(Position target) {
		this.targetResource = (Resource) EntityManager.getInstance().getNonPlayerControlledEntity(target);
		resourceCarriedAmount = 0;
		setActive(true);
		setFinished(false);
	}
	
	@Override
	public AbstractAbility createAbility(PlayerControlledEntity entity, MoveAbility moveAbility) {
		// TODO Plankton: Fix move
		MineResourceAbility newAbility = new MineResourceAbility();
		newAbility.initialize(entity);
		return newAbility;
	}

}
