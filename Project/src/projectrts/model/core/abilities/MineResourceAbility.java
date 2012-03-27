package projectrts.model.core.abilities;

import projectrts.model.core.Position;
import projectrts.model.core.entities.PlayerControlledEntity;
import projectrts.model.core.entities.Resource;
import projectrts.model.core.utils.ModelUtils;
/**
 * An ability for moving to a resource and mine from it
 * @author Jakob Svensson
 *
 */
public class MineResourceAbility extends AbstractAbility{
	private Resource targetResource;
	private Position target; //TODO: Remove this and use targetResource instead 
	private PlayerControlledEntity unit;
	private MoveAbility moveAbility = new MoveAbility();
	
	@Override
	public String getName() {
		return "FetchResource";
	}

	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			
			if(ModelUtils.INSTANCE.getDistance(unit.getPosition(),target )<1){
				//If in range of resource
				
				//TODO: Mine from resource
				setFinished(true);
			}else{
				// Not in range
				
				if(!moveAbility.isActive()){
					moveAbility.useAbility(unit, target);
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
	public void useAbility(PlayerControlledEntity entity, Position target) {
		this.unit = entity;
		this.target =  target;
		setActive(true);
		setFinished(false);
	}

}
