package projectrts.model.abilities;

import projectrts.model.entities.Headquarter;
import projectrts.model.entities.AbstractPlayerControlledEntity;

/**
 * An ability for building Headquarter
 * 
 * @author Jakob Svensson
 * 
 */
class BuildHeadquarterAbility extends AbstractConstructAbility implements
		IMoveable, IBuildStructureAbility {
	private static float buildTime = 30;
	private static int buildCost = 400;

	static {
		AbilityFactory.registerAbility(
				BuildHeadquarterAbility.class.getSimpleName(),
				new BuildHeadquarterAbility());
	}

	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(AbstractPlayerControlledEntity entity, 
			MoveAbility moveAbility) {
		super.initialize(entity, moveAbility);
		this.setBuildCost(buildCost);
		this.setBuildTime(buildTime);
		this.setEntityToTrain(Headquarter.class.getSimpleName());
		this.setSizeOfBuilding(Headquarter.SIZE);
	}

	@Override
	public String getName() {
		return "Build Headquarter";
	}

	@Override
	public AbstractAbility createAbility(AbstractPlayerControlledEntity entity,
			MoveAbility moveAbility) {
		BuildHeadquarterAbility newAbility = new BuildHeadquarterAbility();
		newAbility.initialize(entity, moveAbility);
		return newAbility;
	}

	@Override
	public String getInfo() {
		return "Builds a new Headquarter\nCost: " + buildCost;
	}
}
