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
public abstract class AbstractTrainUnitAbility extends AbstractAbility{
	private PlayerControlledEntity structure;
	private float buildTime; 
	private int buildCost; 
	private String entityName;
	private float buildTimeLeft;
	private Position spawnPos;
	
	
	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			if(buildTimeLeft<=0){
				spawnPos = AStar.getInstance().getClosestUnoccupiedNode(structure.getPosition(), null, 0).getPosition();
				EntityManager.getInstance().addNewPCE(entityName, (Player)structure.getOwner(),spawnPos);
				setFinished(true);
				buildTimeLeft =buildTime;
			}else{
				buildTimeLeft-=tpf;
			}
		}
	}

	@Override
	public void useAbility(PlayerControlledEntity caster, Position target) {
		if(!isActive()){//TODO Jakob: Notify view that ability is already in use(or maybe set in queue?)
			structure = caster;
			Player owner = (Player)structure.getOwner();
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

	protected void setEntityName(String entityName) {
		this.entityName = entityName;
	}


}
