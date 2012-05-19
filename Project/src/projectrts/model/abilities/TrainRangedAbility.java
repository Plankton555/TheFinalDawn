package projectrts.model.abilities;

import projectrts.model.entities.AbstractPlayerControlledEntity;
import projectrts.model.entities.Ranged;

/**
 * A class that trains a ranged unit
 * 
 * @author Jakob Svensson
 * 
 */
class TrainRangedAbility extends AbstractCreationAbility implements
		IStationaryAbility {
	private static float buildTime = 8;
	private static int buildCost = 150;

	static {
		AbilityFactory.registerAbility(
				TrainRangedAbility.class.getSimpleName(),
				new TrainRangedAbility());
	}

	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(AbstractPlayerControlledEntity entity) {
		super.initialize(entity);
		this.setBuildCost(buildCost);
		this.setBuildTime(buildTime);
		this.setEntityToTrain(Ranged.class.getSimpleName());
	}

	@Override
	public String getName() {
		return "Train Ranged";
	}

	@Override
	public AbstractAbility createAbility(AbstractPlayerControlledEntity entity) {
		TrainRangedAbility newAbility = new TrainRangedAbility();
		newAbility.initialize(entity);
		return newAbility;
	}

	@Override
	public String getInfo() {
		return "Trains a new Ranged\nCost: " + buildCost;
	}
}
