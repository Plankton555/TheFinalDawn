package projectrts.model.entities.abilities;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.pathfinding.AStar;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;

/**
 * An abstract class that holds common code for training units
 * @author Jakob Svensson
 *
 */
public abstract class AbstractCreationAbility extends AbstractAbility{

	private float buildTime; 
	private int buildCost; 
	private float buildTimeLeft;
	private Position spawnPos;
	private PlayerControlledEntity entity;
	private String entityToTrain;	
	
	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			if(buildTimeLeft<=0){
				spawnPos = AStar.getInstance().getClosestUnoccupiedNode(entity.getPosition(), null, 0).getPosition();
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
		if(!isActive()){//TODO Jakob: Notify view that ability is already in use(or maybe set in queue?)
			
			Player owner = (Player)entity.getOwner();
			if(owner.getResources()>=buildCost){//TODO Jakob: Notify view somehow when not enough resources
				owner.modifyResource(-buildCost); 
				setActive(true);
				setFinished(false);
				buildTimeLeft=buildTime;
			}
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
	
	protected void setEntity(PlayerControlledEntity entity){
		this.entity = entity;
	}


}
