package projectrts.model.core.abilities;

import java.util.List;

import projectrts.model.core.EntityManager;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.entities.Headquarter;
import projectrts.model.core.entities.IPlayerControlledEntity;
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
	private int resourceCarriedAmount;
	
	@Override
	public String getName() {
		return "DeliverResource";
	}

	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			
			findDepositStructure();
			
			if(ModelUtils.INSTANCE.getDistance(unit.getPosition(),depositStructure.getPosition() )<1){
				//If in range of deposit structure
				
				Player player = (Player)unit.getOwner();
				player.modifyResource(resourceCarriedAmount);
				resourceCarriedAmount = 0;
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
	
	public void setResourceCarriedAmount(int rca){
		this.resourceCarriedAmount=rca;
	}
	
	private void findDepositStructure(){
		List<IPlayerControlledEntity> entities = 
				EntityManager.getInstance().getEntitiesOfPlayer(unit.getOwner());
		
		for(IPlayerControlledEntity e: entities){
			if(e instanceof Headquarter){
				if(depositStructure==null){
					depositStructure = (Headquarter)e;
				}else{
					if(ModelUtils.INSTANCE.getDistance(unit.getPosition(), e.getPosition())
							<ModelUtils.INSTANCE.getDistance(unit.getPosition(), depositStructure.getPosition())){
						//If e is closer to unit than saved depositStructure
						depositStructure = (Headquarter)e;
					}
				}
			}
		}
	}

}
