package projectrts.model.abilities;

import projectrts.model.entities.AbstractPlayerControlledEntity;
import projectrts.model.entities.Wall;

/**
 * An ability for building Barracks
 * 
 * @author Jakob Svensson
 * 
 */
class BuildWallAbility extends AbstractConstructAbility implements IMoveable,
		IBuildStructureAbility {
	private static float buildTime = 1;
	private static int buildCost = 50;

	static {
		AbilityFactory.registerAbility(BuildWallAbility.class.getSimpleName(),
				new BuildWallAbility());
	}

	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(AbstractPlayerControlledEntity entity,
			MoveAbility moveAbility) {
		super.initialize(entity, moveAbility);
		this.setBuildCost(buildCost);
		this.setBuildTime(buildTime);
		this.setEntityToTrain(Wall.class.getSimpleName());
		this.setSizeOfBuilding(Wall.SIZE);
	}

	@Override
	public String getName() {
		return "Build Wall";
	}

	@Override
	public AbstractAbility createAbility(AbstractPlayerControlledEntity entity,
			MoveAbility moveAbility) {
		BuildWallAbility newAbility = new BuildWallAbility();
		newAbility.initialize(entity, moveAbility);
		return newAbility;
	}

	@Override
	public String getInfo() {
		return "Builds a new Wall\nCost: " + buildCost;
	}
}