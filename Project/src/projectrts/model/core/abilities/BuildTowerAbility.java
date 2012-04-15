package projectrts.model.core.abilities;



import projectrts.model.core.EntityManager;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.entities.PlayerControlledEntity;
import projectrts.model.core.entities.Structure;

/**
 * A basic ability for constructing structures
 * @author Filip Brynfors
 *
 */
public class BuildTowerAbility extends AbstractAbility {

	static {
		AbilityFactory.INSTANCE.registerAbility(BuildTowerAbility.class.getSimpleName(), new BuildTowerAbility());
	}
	
	private BuildTowerAbility(){
		super(0.1f);
	}
	
	@Override
	public String getName() {
		return "Build Tower";
	}
	
	@Override
	public void useAbility(PlayerControlledEntity builder, Position pos){
		Player owner = (Player) builder.getOwner();
		Structure struct = new Structure(pos, owner);
		EntityManager.getInstance().addNewPCE("Structure", owner, pos);
		
		this.setAbilityUsed();
		
		//TODO Afton: Check if the position is buildable (nothing else there)
	}

	@Override
	public void update(float tpf) {
		
		
	}

	@Override
	public AbstractAbility createAbility() {
		return new BuildTowerAbility();
	}
}
