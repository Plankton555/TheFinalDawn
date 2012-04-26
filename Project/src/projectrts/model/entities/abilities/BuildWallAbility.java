package projectrts.model.entities.abilities;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.IBuildStructureAbility;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.structures.Wall;
import projectrts.model.pathfinding.World;
import projectrts.model.player.Player;
import projectrts.model.utils.ModelUtils;
import projectrts.model.utils.Position;

//TODO Jakob: Make abstract constructBuilding class
/**
 * An ability for building Barracks
 * @author Jakob Svensson
 *
 */
public class BuildWallAbility extends AbstractAbility implements IMovableAbility, IBuildStructureAbility{
	private PlayerControlledEntity builder;
	private static float buildTime = 1; 
	private static int buildCost = 50; 
	private static float cooldown = 0.5f;
	private Position buildPos;
	private float buildTimeLeft;
	private AbstractAbility moveAbility;
	private float size = 1; //TODO: Sync with Barracks class

	static {
		AbilityFactory.INSTANCE.registerAbility(BuildWallAbility.class.getSimpleName(), new BuildWallAbility());
	}

	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(PlayerControlledEntity entity, MoveAbility moveAbility) {
		builder = entity;
		this.setCooldown(cooldown);
		this.moveAbility = moveAbility;
	}

	@Override
	public String getName() {
		return BuildWallAbility.class.getSimpleName();
	}

	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			if(ModelUtils.INSTANCE.getDistance(builder.getPosition(),buildPos)<1.5){
				//If in range of buildingPosition
				moveAbility.setFinished(true);
				if(buildTimeLeft<=0){
					EntityManager.getInstance().addNewPCE(Wall.class.getSimpleName(), (Player)builder.getOwner(),buildPos);
					setFinished(true);
					buildTimeLeft =buildTime;
				}else{
					buildTimeLeft-=tpf;
				}
			}else{
				// Not in range
				
				if(!moveAbility.isActive()){
					moveAbility.useAbility(buildPos);
				}
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
			World.getInstance().setNodesOccupied(World.getInstance().getNodeAt(target)//TODO: Set unoccupied if ability is aborted
					, getSizeOfBuilding(), EntityManager.getInstance().requestNewEntityID());
		}
	}

	@Override

	public AbstractAbility createAbility(PlayerControlledEntity entity, MoveAbility moveAbility) {
		BuildWallAbility newAbility = new BuildWallAbility();
		newAbility.initialize(entity, moveAbility);
		return newAbility;
	}

	@Override
	public float getSizeOfBuilding() {
		return size;
	}

}
