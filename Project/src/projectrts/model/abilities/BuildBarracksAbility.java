package projectrts.model.abilities;

import projectrts.model.entities.Barracks;
import projectrts.model.entities.PlayerControlledEntity;

/**
 * An ability for building Barracks
 * 
 * @author Jakob Svensson
 * 
 */
class BuildBarracksAbility extends AbstractConstructAbility implements
		IMoveable, IBuildStructureAbility {
	private static float buildTime = 20;
	private static int buildCost = 200;

	static {
		AbilityFactory.registerAbility(
				BuildBarracksAbility.class.getSimpleName(),
				new BuildBarracksAbility());
	}

	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(PlayerControlledEntity entity,
			MoveAbility moveAbility) {
		super.initialize(entity, moveAbility);
		this.setBuildCost(buildCost);
		this.setBuildTime(buildTime);
		this.setEntityToTrain(Barracks.class.getSimpleName());
		this.setSizeOfBuilding(Barracks.SIZE);
	}

	@Override
	public String getName() {
		return "Build Barracks";
	}

	@Override
	public AbstractAbility createAbility(PlayerControlledEntity entity,
			MoveAbility moveAbility) {
		BuildBarracksAbility newAbility = new BuildBarracksAbility();
		newAbility.initialize(entity, moveAbility);
		return newAbility;
	}

	@Override
	public String getInfo() {
		return "Builds a new barrack.\nCost: " + buildCost;
	}
}