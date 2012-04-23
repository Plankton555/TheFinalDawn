package projectrts.model.entities.abilities;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.pathfinding.AStar;
import projectrts.model.pathfinding.World;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;

/**
 * A class that trains a unit
 * @author Jakob Svensson
 *
 */
public class TrainWorkerAbility extends AbstractAbility{
	private PlayerControlledEntity structure;
	private static float buildTime = 5; 
	private static int buildCost = 50; 
	private static float cooldown = .5f;
	private float buildTimeLeft;
	private Position spawnPos;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(TrainWorkerAbility.class.getSimpleName(), new TrainWorkerAbility());
	}
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize() {
		this.setCooldown(cooldown);
	}
	
	@Override
	public String getName() {
		return "TrainWorker";
	}

	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			if(buildTimeLeft<=0){
				EntityManager.getInstance().addNewPCE("Worker", (Player)structure.getOwner(),spawnPos);
				setFinished(true);
				buildTimeLeft =buildTime;
			}else{
				buildTimeLeft-=tpf;
			}
			System.out.println(buildTimeLeft);
		}
	}

	@Override
	public void useAbility(PlayerControlledEntity caster, Position target) {
		structure = caster;
		Player owner = (Player)structure.getOwner();
		if(owner.getResources()>=buildCost){//TODO Jakob: Notify view somehow when not enough resources
			owner.modifyResource(-buildCost); 
			spawnPos = AStar.getInstance().getClosestUnoccupiedNode(structure.getPosition(), null, 0).getPosition();
			setActive(true);
			setFinished(false);
			buildTimeLeft=buildTime;
		}
	}

	@Override
	public AbstractAbility createAbility() {
		TrainWorkerAbility newAbility = new TrainWorkerAbility();
		newAbility.initialize();
		return newAbility;
	}

}
