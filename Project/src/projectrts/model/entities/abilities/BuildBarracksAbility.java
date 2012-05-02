package projectrts.model.entities.abilities;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.IBuildStructureAbility;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.structures.Barracks;

/**
 * An ability for building Barracks
 * @author Jakob Svensson
 *
 */
public class BuildBarracksAbility extends AbstractConstructAbility implements IUsingMoveAbility, IBuildStructureAbility {
	private static float buildTime = 1; 
	private static int buildCost = 200; 
	private float size = 3; //TODO Jakob: Sync with Barracks class


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
		this.setSizeOfBuilding(size);
	}

	@Override
	public String getName() {
		return BuildBarracksAbility.class.getSimpleName();
	}


	@Override

	public AbstractAbility createAbility(IPlayerControlledEntity entity, MoveAbility moveAbility) {
		BuildBarracksAbility newAbility = new BuildBarracksAbility();
		newAbility.initialize(entity, moveAbility);
		return newAbility;
	}

}
