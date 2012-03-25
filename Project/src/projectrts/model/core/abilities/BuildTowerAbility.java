package projectrts.model.core.abilities;



import projectrts.model.core.EntityManager;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.entities.IPlayerControlledEntity;
import projectrts.model.core.entities.Structure;

/**
 * A basic ability for constructing structures
 * @author Filip Brynfors
 *
 */
public class BuildTowerAbility extends AbstractAbility {

	public BuildTowerAbility(){
		super(0.1f);
	}
	
	@Override
	public String getName() {
		return "Build Tower";
	}
	
	public void doAbility(IPlayerControlledEntity builder, Position pos){
		Player owner = (Player) builder.getOwner();
		Structure struct = new Structure(pos, owner);
		EntityManager.getInstance().addEntity(struct);
		
		this.setAbilityUsed();
		
		//TODO: Check if the position is buildable (nothing else there)
	}

}
