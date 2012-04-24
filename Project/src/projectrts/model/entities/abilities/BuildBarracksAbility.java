package projectrts.model.entities.abilities;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.structures.Barracks;

/**
 * An ability for building Barracks
 * @author Jakob Svensson
 *
 */
public class BuildBarracksAbility extends AbstractCreationAbility{
	private static float buildTime = 10; 
	private static int buildCost = 200; 
	private static float cooldown = 0.5f;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(BuildBarracksAbility.class.getSimpleName(), new BuildBarracksAbility());
	}
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize() {
		this.setCooldown(cooldown);
		this.setBuildCost(buildCost);
		this.setBuildTime(buildTime);
		this.setEntityName(Barracks.class.getSimpleName());
	}
	
	@Override
	public String getName() {
		return BuildBarracksAbility.class.getSimpleName();
	}

	@Override
	public AbstractAbility createAbility() {
		BuildBarracksAbility newAbility = new BuildBarracksAbility();
		newAbility.initialize();
		return newAbility;
	}

}
