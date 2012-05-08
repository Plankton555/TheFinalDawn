package projectrts.model.abilities;

import projectrts.model.entities.Barracks;
import projectrts.model.entities.PlayerControlledEntity;

/**
 * An ability for building Barracks
 * @author Jakob Svensson
 *
 */
public class BuildBarracksAbility extends AbstractConstructAbility implements IUsingMoveAbility, IBuildStructureAbility {
	private static float buildTime = 20; 
	private static int buildCost = 200; 


	static {
		AbilityFactory.INSTANCE.registerAbility(BuildBarracksAbility.class.getSimpleName(), new BuildBarracksAbility());
	}

	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(PlayerControlledEntity entity, MoveAbility moveAbility) {
		super.initialize(entity, moveAbility);
		this.setBuildCost(buildCost);
		this.setBuildTime(buildTime);
		this.setEntityToTrain(Barracks.class.getSimpleName());
		this.setSizeOfBuilding(Barracks.SIZE);
	}

	@Override
	public String getName() {
		return BuildBarracksAbility.class.getSimpleName();
	}


	@Override

	public AbstractAbility createAbility(PlayerControlledEntity entity, MoveAbility moveAbility) {
		BuildBarracksAbility newAbility = new BuildBarracksAbility();
		newAbility.initialize(entity, moveAbility);
		return newAbility;
	}

}
