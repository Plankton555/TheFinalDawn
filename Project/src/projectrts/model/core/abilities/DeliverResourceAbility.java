package projectrts.model.core.abilities;

import projectrts.model.core.Position;
import projectrts.model.core.entities.PlayerControlledEntity;
import projectrts.model.core.utils.ModelUtils;
/**
 * An ability for delivering a resource at a deposit structure
 * @author Jakob Svensson
 *
 */
public class DeliverResourceAbility extends AbstractAbility{
	
	private PlayerControlledEntity unit;
	private PlayerControlledEntity depositStructure;
	private MoveAbility moveAbility = new MoveAbility();
	
	@Override
	public String getName() {
		return "DeliverResource";
	}

	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			
			//TODO: Find closest deposit structure
			
			if(ModelUtils.INSTANCE.getDistance(unit.getPosition(),depositStructure.getPosition() )<1){
				//If in range of deposit structure
				
				//TODO: deliver resources
				setFinished(true);
			}else{
				// Not in range
				
				if(!moveAbility.isActive()){
					moveAbility.useAbility(unit, depositStructure.getPosition());
				}
				
				moveAbility.update(tpf);
				if(moveAbility.isFinished()){
					moveAbility.setActive(false);
					moveAbility.setFinished(false);
				}
			}
		}
		
	}

	@Override
	public void useAbility(PlayerControlledEntity caster, Position target) {
		this.unit=caster;
		setActive(true);
		setFinished(false);
		
	}

}
