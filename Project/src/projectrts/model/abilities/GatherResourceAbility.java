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
// TODO Jakob: PMD: This class has too many methods, consider refactoring it.
public class GatherResourceAbility extends AbstractAbility implements IUsingMoveAbility, ITargetAbility{
	
	private AbstractAbility mineResourceAbility;
	private AbstractAbility deliverResourceAbility;
	private Position target;
	public static final int RESOURCE_CARRIED_AMOUNT = 12;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(GatherResourceAbility.class.getSimpleName(), new GatherResourceAbility());
		
	}
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(PlayerControlledEntity entity, MoveAbility moveAbility) {
		this.mineResourceAbility = AbilityFactory.INSTANCE.createUsingMoveAbility(MineResourceAbility.class.getSimpleName(), entity, moveAbility);
		this.deliverResourceAbility = AbilityFactory.INSTANCE.createUsingMoveAbility(DeliverResourceAbility.class.getSimpleName(), entity, moveAbility);
	}

	
	@Override
	public String getName() {
		return "Gather Resources";
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
			if(EntityManager.INSTANCE.getNPCEAtPosition(target) instanceof Resource){
			setActive(true);
			setFinished(false);
			if(!deliverResourceAbility.isActive()){
				mineResourceAbility.useAbility(target);
			}
		}else{
			pcs.firePropertyChange("TargetNotResource", null, null);
		}
	}

	@Override
	public AbstractAbility createAbility(PlayerControlledEntity entity, MoveAbility moveAbility) {
		GatherResourceAbility newAbility = new GatherResourceAbility();
		AbilityFactory.INSTANCE.registerAbility(DeliverResourceAbility.class.getSimpleName(), new DeliverResourceAbility());
		AbilityFactory.INSTANCE.registerAbility(MineResourceAbility.class.getSimpleName(), new MineResourceAbility());
		newAbility.initialize(entity, moveAbility);
		return newAbility;
	}
	
	/**
	 * An ability for delivering a resource at a deposit structure
	 * @author Jakob Svensson
	 *
	 */
	public class DeliverResourceAbility extends AbstractAbility implements IUsingMoveAbility{
		
		private PlayerControlledEntity entity;
		private AbstractStructure depositStructure;
		private AbstractAbility moveAbility;
		// TODO Jakob: PMD: Private field 'range' could be made final; it is only initialized in the declaration or constructor.
		private double range = 1;
		
		
		/**
		 * When subclassing, invoke this to initialize the ability.
		 */
		protected void initialize(PlayerControlledEntity entity, MoveAbility moveAbility) {
			this.entity=entity;
			this.moveAbility = moveAbility;
		}
		
		@Override
		public String getName() {
			return "DeliverResource";
		}

		@Override
		public void update(float tpf) {
			if(isActive() && !isFinished()){
				
				findDepositStructure();
				if(depositStructure!=null){
					//if(Position.getDistance(entity.getPosition(),depositStructure.getPosition() )<1.5*depositStructure.getSize()){
					if(inRange(depositStructure)){
						//If in range of deposit structure
						moveAbility.setFinished(true);
						
						Player player = (Player)entity.getOwner();
						player.modifyResource(RESOURCE_CARRIED_AMOUNT);
						setFinished(true);
					}else{
						// Not in range
						// TODO Jakob: PMD: Deeply nested if..then statements are hard to read
						if(!moveAbility.isActive()){
							moveAbility.useAbility(depositStructure.getPosition());
						}
					}
				}
			}
			
		}

		@Override
		public void useAbility(Position target) {
			setActive(true);
			setFinished(false);
			
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
							//System.out.println(e.getPosition());
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
				// TODO Jakob: PMD: Assigning an Object to null is a code smell. Consider refactoring.
				depositStructure = null;
				abortAbility();
			}
		}

		@Override
		public AbstractAbility createAbility(PlayerControlledEntity entity, MoveAbility moveAbility) {
			DeliverResourceAbility newAbility = new DeliverResourceAbility();
			newAbility.initialize(entity, moveAbility);
			return newAbility;
			
		}

		private boolean inRange(AbstractEntity target)
		{
			
			return (Position.getDistance(entity.getPosition(), target.getPosition()) < range  + (target.getSize()/2)*1.5);
		}

		@Override
		public String getInfo() {
			return "Delivers a resource";
		}


	}
	
	/**
	 * An ability for moving to a resource and mine from it
	 * @author Jakob Svensson
	 *
	 */
	public class MineResourceAbility extends AbstractAbility implements IUsingMoveAbility {
		private Resource targetResource;
		private PlayerControlledEntity entity;
		private AbstractAbility moveAbility;
		private int resourceCarriedAmount = 0;
		// TODO Jakob: PMD: This final field could be made static
		private final float recoveryTime = .4f;
		private float miningCooldown = 0;
		// TODO Jakob: PMD: Private field 'range' could be made final; it is only initialized in the declaration or constructor.
		private int range = 1;
		
		/**
		 * When subclassing, invoke this to initialize the ability.
		 */
		protected void initialize(PlayerControlledEntity entity, MoveAbility moveAbility) {
			this.entity = entity;
			this.moveAbility = moveAbility;
		}
		
		@Override
		public String getName() {
			return "FetchResource";
		}

		@Override
		public void update(float tpf) {
			if(isActive() && !isFinished()){
				
				if(inRange(targetResource)){
					//If in range of resource
					//Check cooldown and mine resource or reduce cooldown as appropriate.
					moveAbility.setFinished(true);
					if (miningCooldown <= 0) { 
						resourceCarriedAmount += targetResource.mine();
						miningCooldown = recoveryTime;
					} else {
						miningCooldown -= tpf;
					}
					
					if(resourceCarriedAmount >= RESOURCE_CARRIED_AMOUNT) {
						setFinished(true);
					}
					
				}else{
					// Not in range
					if(!moveAbility.isActive()){
						moveAbility.useAbility(targetResource.getPosition());
					}
				}
			}
			
		}

		@Override
		public void useAbility(Position target) {
			this.targetResource = (Resource) EntityManager.INSTANCE.getNPCEAtPosition(target);
			resourceCarriedAmount = 0;
			setActive(true);
			setFinished(false);
		}
		
		@Override
		public AbstractAbility createAbility(PlayerControlledEntity entity, MoveAbility moveAbility) {
			MineResourceAbility newAbility = new MineResourceAbility();
			newAbility.initialize(entity, moveAbility);
			return newAbility;
		}

		private boolean inRange(AbstractEntity target)
		{
			return (Position.getDistance(entity.getPosition(), target.getPosition()) < range + (target.getSize()/2)*1.5);
		}

		@Override
		public String getInfo() {
			return "Fetches a resource";
		}
		
	}

	@Override
	public String getInfo() {
		return "Gathers a resource";
	}
	
}
