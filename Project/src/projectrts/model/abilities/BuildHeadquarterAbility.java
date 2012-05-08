package projectrts.model.abilities;

import projectrts.model.entities.Headquarter;
import projectrts.model.entities.PlayerControlledEntity;

/**
 * An ability for building Barracks
 * @author Jakob Svensson
 *
 */
public class BuildHeadquarterAbility extends AbstractConstructAbility implements IUsingMoveAbility, IBuildStructureAbility {
	private static float buildTime = 30; 
	private static int buildCost = 400; 
	// TODO Jakob: PMD: Private field 'size' could be made final; it is only initialized in the declaration or constructor.
	private float size = 3; //TODO Jakob: Sync with Barracks class


	static {
		AbilityFactory.INSTANCE.registerAbility(BuildHeadquarterAbility.class.getSimpleName(), new BuildHeadquarterAbility());
	}

	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(PlayerControlledEntity entity, MoveAbility moveAbility) {
		super.initialize(entity, moveAbility);
		this.setBuildCost(buildCost);
		this.setBuildTime(buildTime);
		this.setEntityToTrain(Headquarter.class.getSimpleName());
		this.setSizeOfBuilding(size);
	}

	@Override
	public String getName() {
		return BuildHeadquarterAbility.class.getSimpleName();
	}


	@Override

	public AbstractAbility createAbility(PlayerControlledEntity entity, MoveAbility moveAbility) {
		BuildHeadquarterAbility newAbility = new BuildHeadquarterAbility();
		newAbility.initialize(entity, moveAbility);
		return newAbility;
	}

}
