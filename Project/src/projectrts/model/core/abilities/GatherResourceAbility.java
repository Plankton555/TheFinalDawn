package projectrts.model.core.abilities;

import projectrts.model.core.Position;
import projectrts.model.core.entities.PlayerControlledEntity;
/**
 * An ability for gathering resources
 * @author Jakob Svensson
 *
 */
public class GatherResourceAbility extends AbstractAbility{
	
	private PlayerControlledEntity unit;
	private MineResourceAbility mineResourceAbility = new MineResourceAbility();
	private DeliverResourceAbility deliverResourceAbility = new DeliverResourceAbility();
	private Position target;
	
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
		mineResourceAbility.useAbility(unit, target);
	}

}
