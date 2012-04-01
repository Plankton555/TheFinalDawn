package projectrts.model.core.abilities;

import projectrts.model.core.EntityManager;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.entities.PlayerControlledEntity;

/**
 * A class that trains a unit
 * @author Jakob Svensson
 *
 */
public class TainWorkerAbility extends AbstractAbility{
	private PlayerControlledEntity structure;
	private float buildTime = 100;
	
	@Override
	public String getName() {
		return "TrainWorker";
	}

	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			if(buildTime<=0){
				EntityManager.getInstance().addNewPCE("Worker",
						(Player)structure.getOwner(),structure.getPosition());
				setFinished(true);
				buildTime =100;
			}else{
				buildTime-=tpf;
			}
		}
	}

	@Override
	public void useAbility(PlayerControlledEntity caster, Position target) {
		structure = caster;
		setActive(true);
		setFinished(false);
	}

}
