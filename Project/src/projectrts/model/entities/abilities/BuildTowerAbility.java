package projectrts.model.entities.abilities;



import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;

/**
 * A basic ability for constructing structures
 * @author Filip Brynfors
 *
 */
public class BuildTowerAbility extends AbstractAbility {

	static {
		AbilityFactory.INSTANCE.registerAbility(BuildTowerAbility.class.getSimpleName(), new BuildTowerAbility());
	}

	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize() {
		this.setCooldown(0.5f);
	}
	
	@Override
	public String getName() {
		return "Build Tower";
	}
	
	@Override
	public void useAbility(PlayerControlledEntity builder, Position pos){
		Player owner = (Player) builder.getOwner();
		EntityManager.getInstance().addNewPCE("Structure", owner, pos);
		
		this.setAbilityUsed();
		
		//TODO Afton: Check if the position is buildable (nothing else there)
	}

	@Override
	public void update(float tpf) {
		
		
	}

	@Override
	public AbstractAbility createAbility() {
		BuildTowerAbility newAbility = new BuildTowerAbility();
		newAbility.initialize();
		return newAbility;
	}
}
