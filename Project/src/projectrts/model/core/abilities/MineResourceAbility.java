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
	private PlayerControlledEntity unit;
	private MoveAbility moveAbility = new MoveAbility();
	private int resourceCarriedAmount;
	
	@Override
	public String getName() {
		return "FetchResource";
	}

	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			
			if(ModelUtils.INSTANCE.getDistance(unit.getPosition(),targetResource.getPosition() )<1){
				//If in range of resource
				
				resourceCarriedAmount = targetResource.mine();
				setFinished(true);
			}else{
				// Not in range
				
				if(!moveAbility.isActive()){
					moveAbility.useAbility(unit, targetResource.getPosition());
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
		this.targetResource = (Resource) ModelUtils.INSTANCE.getNonPlayerControlledEntity(target);
		resourceCarriedAmount = 0;
		setActive(true);
		setFinished(false);
	}
	
	// TODO Anyone: Should Mine-, Deliver- and GatherResource be different abilities?.. Can you do one without doing the others?..
	// TODO Jakob: Add javadoc
	public int getResourceCarriedAmount(){
		return resourceCarriedAmount;
	}

}
