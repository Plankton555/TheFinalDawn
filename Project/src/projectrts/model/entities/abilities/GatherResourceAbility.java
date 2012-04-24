package projectrts.model.entities.abilities;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.utils.Position;
/**
 * An ability for gathering resources
 * @author Jakob Svensson
 *
 */
public class GatherResourceAbility extends AbstractAbility{
	
	private PlayerControlledEntity unit;
	private AbstractAbility mineResourceAbility;
	private AbstractAbility deliverResourceAbility;
	private Position target;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(GatherResourceAbility.class.getSimpleName(), new GatherResourceAbility());
	}
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(PlayerControlledEntity entity) {
		this.mineResourceAbility = AbilityFactory.INSTANCE.createAbility(MineResourceAbility.class.getSimpleName(), entity);
		this.deliverResourceAbility = AbilityFactory.INSTANCE.createAbility(DeliverResourceAbility.class.getSimpleName(), entity);
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
					deliverResourceAbility.useAbility(unit, target);
				}
				if(deliverResourceAbility.isFinished()){
					deliverResourceAbility.setActive(false);
					deliverResourceAbility.setFinished(false);
					mineResourceAbility.useAbility(unit, target);
				}
			
		}
		
	}

	@Override
	public void useAbility(PlayerControlledEntity caster, Position target) {
		this.unit =  caster;
		this.target = target;
		setActive(true);
		setFinished(false);
		if(!deliverResourceAbility.isActive()){
			mineResourceAbility.useAbility(unit, target);
		}
	}

	@Override
	public AbstractAbility createAbility(PlayerControlledEntity entity) {
		GatherResourceAbility newAbility = new GatherResourceAbility();
		newAbility.initialize(entity);
		return newAbility;
	}

}
