package projectrts.model.abilities;

import java.util.List;

import projectrts.model.entities.AbstractEntity;
import projectrts.model.entities.AbstractStructure;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.entities.Player;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.Resource;
import projectrts.model.world.Position;
/**
 * An ability for gathering resources
 * @author Jakob Svensson
 *
 */
public class GatherResourceAbility extends AbstractAbility implements IUsingMoveAbility, ITargetAbility{
	
	public static final int RESOURCE_CARRIED_AMOUNT = 12;
	private static final int RANGE = 1;
	private Resource targetResource;
	private PlayerControlledEntity entity;
	private AbstractAbility moveAbility;
	private int resourceCarriedAmount = 0;
	private static final float RECOVERY_TIME = .4f;
	private float miningCooldown = 0;
	private boolean miningFinished=false;
	private boolean deliverFinished=false;
	private AbstractStructure depositStructure;
	
	
	static {
		AbilityFactory.registerAbility(GatherResourceAbility.class.getSimpleName(), new GatherResourceAbility());
		
	}
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(PlayerControlledEntity entity, MoveAbility moveAbility) {

		this.moveAbility=moveAbility;
		this.entity=entity;
	}

	
	@Override
	public String getName() {
		return "Gather Resources";
	}

	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
				
				if(miningFinished&&!deliverFinished){
					updateDeliverResource(tpf);
				}else{
					updateMineResource(tpf);
				}
		}
		
	}

	@Override
	public void useAbility(Position target) {
		if(EntityManager.INSTANCE.getNPCEAtPosition(target) instanceof Resource){
			this.targetResource = (Resource) EntityManager.INSTANCE.getNPCEAtPosition(target);
			resourceCarriedAmount = 0;
			setActive(true);
			setFinished(false);
			if(deliverFinished){
				miningFinished=false;
				deliverFinished=true;
			}
		}else{
			pcs.firePropertyChange("TargetNotResource", null, null);
		}
	}

	@Override
	public AbstractAbility createAbility(PlayerControlledEntity entity, MoveAbility moveAbility) {
		GatherResourceAbility newAbility = new GatherResourceAbility();
		newAbility.initialize(entity, moveAbility);
		return newAbility;
	}
	
		
		public void updateDeliverResource(float tpf) {
			findDepositStructure();
			if(depositStructure!=null){
				if(inRange(depositStructure)){
					moveAbility.setFinished(true);
					
					Player player = (Player)entity.getOwner();
					player.modifyResource(RESOURCE_CARRIED_AMOUNT);
					deliverFinished=true;
					miningFinished=false;
					resourceCarriedAmount=0;
				}else{
					useMoveAbility();
				}
			}
		}
			
		private void useMoveAbility(){
			if(!moveAbility.isActive()){
				moveAbility.useAbility(depositStructure.getPosition());
			}
		}
		
		private void findDepositStructure(){
			List<IPlayerControlledEntity> entities = EntityManager.INSTANCE.getEntitiesOfPlayer(entity.getOwner());
		
		
			for(IPlayerControlledEntity e: entities){
				if(e instanceof AbstractStructure){
					AbstractStructure struct = (AbstractStructure)e;
					if(struct.isDeposit()){
						if(depositStructure == null) {
							depositStructure = struct;
						}else{
							if(Position.getDistance(entity.getPosition(), e.getPosition())
								<Position.getDistance(entity.getPosition(), depositStructure.getPosition())){
								//If e is closer to unit than saved depositStructure
								depositStructure = struct;
							}
						}
					}
				}
			}
			
			if(depositStructure==null || depositStructure.isDead()){
				depositStructure = null;
				abortAbility();
			}
		}

		private void updateMineResource(float tpf) {
			if(inRange(targetResource)){
				//Check cooldown and mine resource or reduce cooldown as appropriate.
				moveAbility.setFinished(true);
				if (miningCooldown <= 0) { 
					resourceCarriedAmount += targetResource.mine();
					miningCooldown = RECOVERY_TIME;
				} else {
					miningCooldown -= tpf;
				}
				if(resourceCarriedAmount >= RESOURCE_CARRIED_AMOUNT) {
					miningFinished = true;
					deliverFinished = false;
				}
				
			}else{
				if(!moveAbility.isActive()){
					moveAbility.useAbility(targetResource.getPosition());
				}
			}
		}
		
		

		private boolean inRange(AbstractEntity target)
		{
			return (Position.getDistance(entity.getPosition(), target.getPosition()) < RANGE + (target.getSize()/2)*1.5);
		}

	

	@Override
	public String getInfo() {
		return "Gathers a resource";
	}
	
}
