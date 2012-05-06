package projectrts.model.abilities;

import projectrts.model.abilities.pathfinding.AStar;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Player;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.world.Position;

/**
 * An abstract class that holds common code for training units
 * @author Jakob Svensson
 *
 */
public abstract class AbstractCreationAbility extends AbstractAbility{

	private float buildTime; 
	private int buildCost; 
	private float buildTimeLeft;
	private static float cooldown = 0.5f;
	private Position spawnPos;
	private PlayerControlledEntity entity;
	private String entityToTrain;	
	
	
	protected void initialize(PlayerControlledEntity entity) {
		this.entity=entity;
		this.setCooldown(cooldown);
	}
	
	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			if(buildTimeLeft<=0){
				spawnPos = AStar.getClosestUnoccupiedNode(entity.getPosition(), null, 0).getPosition();
				EntityManager.getInstance().addNewPCE(entityToTrain, (Player)entity.getOwner(),spawnPos);
				setFinished(true);
				buildTimeLeft =buildTime;
			}else{
				buildTimeLeft-=tpf;
			}
		}
	}

	@Override

	public void useAbility(Position target) {
		// TODO Jakob: PMD: Avoid if (x != y) ..; else ..;
		if(!isActive()){
			Player owner = (Player)entity.getOwner();
			if(owner.getResources()>=buildCost){
				owner.modifyResource(-buildCost); 
				setActive(true);
				setFinished(false);
				buildTimeLeft=buildTime;
			}else{
				pcs.firePropertyChange("NotEnoughResources", null, null);
			}
		}else{
			pcs.firePropertyChange("AlreadyTraining", null, null);
		}
	}
	
	protected void setBuildTime(float buildTime) {
		this.buildTime = buildTime;
	}

	protected void setBuildCost(int buildCost) {
		this.buildCost = buildCost;
	}
	
	protected void setEntityToTrain(String name){
		this.entityToTrain=name;
	}
	
	@Override
	// TODO Jakob: PMD: An empty method in an abstract class should be abstract instead
	public void abortAbility(){
		//Override so it won't be aborted if the ability is used again before it's finished
	}
	
}
