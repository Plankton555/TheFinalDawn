package projectrts.model.entities.abilities;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.structures.Barracks;
import projectrts.model.player.Player;
import projectrts.model.utils.ModelUtils;
import projectrts.model.utils.Position;

/**
 * An ability for building Barracks
 * @author Jakob Svensson
 *
 */
public class BuildBarracksAbility extends AbstractAbility implements IMovableAbility {
	private PlayerControlledEntity builder;
	private static float buildTime = 1; 
	private static int buildCost = 200; 
	private static float cooldown = 0.5f;
	private Position buildPos;
	private float buildTimeLeft;
	private AbstractAbility moveAbility;

	static {
		AbilityFactory.INSTANCE.registerAbility(BuildBarracksAbility.class.getSimpleName(), new BuildBarracksAbility());
	}

	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(PlayerControlledEntity entity) {
		builder = entity;
		this.setCooldown(cooldown);
		this.moveAbility = AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName(), entity);
	}

	@Override
	public String getName() {
		return BuildBarracksAbility.class.getSimpleName();
	}

	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			if(ModelUtils.INSTANCE.getDistance(builder.getPosition(),buildPos)<3){
				//If in range of buildingPosition
				if(buildTimeLeft<=0){
					EntityManager.getInstance().addNewPCE(Barracks.class.getSimpleName(), (Player)builder.getOwner(),buildPos);
					setFinished(true);
					buildTimeLeft =buildTime;
				}else{
					buildTimeLeft-=tpf;
				}
				System.out.println(buildTimeLeft);
			}else{
				// Not in range
				
				if(!moveAbility.isActive()){
					moveAbility.useAbility(buildPos);
				}
				
				moveAbility.update(tpf);
			}
		}
	}

	@Override
	public void useAbility(Position target) {
		Player owner = (Player)builder.getOwner();
		if(owner.getResources()>=buildCost){//TODO Jakob: Notify view somehow when not enough resources
			owner.modifyResource(-buildCost); 
			buildPos = target;
			setActive(true);
			setFinished(false);
			buildTimeLeft=buildTime;
		}
	}

	@Override

	public AbstractAbility createAbility(PlayerControlledEntity entity, MoveAbility moveAbility) {
		// TODO Plankton: Fix move
		BuildBarracksAbility newAbility = new BuildBarracksAbility();
		newAbility.initialize(entity);
		return newAbility;
	}

}
