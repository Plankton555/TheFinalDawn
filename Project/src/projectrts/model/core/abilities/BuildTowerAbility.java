package projectrts.model.core.abilities;



import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.entities.IPlayerControlledEntity;
import projectrts.model.core.entities.Structure;

/**
 * A basic ability for constructing structures
 * @author Filip Brynfors
 *
 */
public class BuildTowerAbility implements IAbility {

	@Override
	public String getName() {
		return "Build Tower";
	}
	
	@Override
	public float getCooldown() {
		return 0.1f;
	}
	
	public void doAbility(IPlayerControlledEntity builder, Position pos){
		Player owner = (Player) builder.getOwner();
		Structure struct = new Structure(pos, owner);
		
		//TODO: Add to player in EntityManager and check if the position is buildable (nothing else there)
	}

}
