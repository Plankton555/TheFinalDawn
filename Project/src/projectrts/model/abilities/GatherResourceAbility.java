package projectrts.model.abilities;

import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.utils.Position;
/**
 * An ability for gathering resources
 * @author Jakob Svensson
 *
 */
public class GatherResourceAbility extends AbstractAbility implements IUsingMoveAbility, ITargetAbility{
	
	private AbstractAbility mineResourceAbility;
	private AbstractAbility deliverResourceAbility;
	private Position target;
	private static final int resourceCarriedAmount = 12;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(GatherResourceAbility.class.getSimpleName(), new GatherResourceAbility());
	}
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(IPlayerControlledEntity entity, MoveAbility moveAbility) {
		this.mineResourceAbility = AbilityFactory.INSTANCE.createUsingMoveAbility(MineResourceAbility.class.getSimpleName(), entity, moveAbility);
		this.deliverResourceAbility = AbilityFactory.INSTANCE.createUsingMoveAbility(DeliverResourceAbility.class.getSimpleName(), entity, moveAbility);
	}

	
	@Override
	public String getName() {
		return "GatherResource";
	}

	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			
				mineResourceAbility.update(tpf);
				deliverResourceAbility.update(tpf);
				if(mineResourceAbility.isFinished()){
					mineResourceAbility.setActive(false);
					mineResourceAbility.setFinished(false);
					deliverResourceAbility.useAbility(target);
				}
				if(deliverResourceAbility.isFinished()){
					deliverResourceAbility.setActive(false);
					deliverResourceAbility.setFinished(false);
					mineResourceAbility.useAbility(target);
				}
			
		}
		
	}

	@Override
	public void useAbility(Position target) {
		this.target = target;
		setActive(true);
		setFinished(false);
		if(!deliverResourceAbility.isActive()){
			mineResourceAbility.useAbility(target);
		}
	}

	@Override
	public AbstractAbility createAbility(IPlayerControlledEntity entity, MoveAbility moveAbility) {
		GatherResourceAbility newAbility = new GatherResourceAbility();
		newAbility.initialize(entity, moveAbility);
		return newAbility;
	}
	
	public int getResourceCarriedAmount(){
		return resourceCarriedAmount;
	}

}