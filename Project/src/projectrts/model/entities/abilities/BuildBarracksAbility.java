package projectrts.model.entities.abilities;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.structures.Barracks;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;

/**
 * An ability for building Barracks
 * @author Jakob Svensson
 *
 */
public class BuildBarracksAbility extends AbstractAbility{
	private PlayerControlledEntity builder;
	private static float buildTime = 10; 
	private static int buildCost = 200; 
	private static float cooldown = 0.5f;
	private Position buildPos;
	private float buildTimeLeft;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(BuildBarracksAbility.class.getSimpleName(), new BuildBarracksAbility());
	}
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize() {
		this.setCooldown(cooldown);
	}
	
	@Override
	public String getName() {
		return BuildBarracksAbility.class.getSimpleName();
	}

	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			if(buildTimeLeft<=0){
				EntityManager.getInstance().addNewPCE(Barracks.class.getSimpleName(), (Player)builder.getOwner(),buildPos);
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
		builder = caster;
		Player owner = (Player)builder.getOwner();
		if(owner.getResources()>=buildCost){//TODO Jakob: Notify view somehow when not enough resources
			owner.modifyResource(-buildCost); 
			buildPos = target;
			setActive(true);
			setFinished(false);
			buildTimeLeft=buildTime;
		}
	}

	@Override
	public AbstractAbility createAbility() {
		BuildBarracksAbility newAbility = new BuildBarracksAbility();
		newAbility.initialize();
		return newAbility;
	}

}
