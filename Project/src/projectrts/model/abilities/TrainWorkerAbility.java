package projectrts.model.abilities;

import projectrts.model.entities.AbstractPlayerControlledEntity;
import projectrts.model.entities.Worker;

/**
 * A class that trains a unit
 * 
 * @author Jakob Svensson
 * 
 */
class TrainWorkerAbility extends AbstractCreationAbility implements
		IStationaryAbility {
	private static float buildTime = 8;
	private static int buildCost = 50;

	static {
		AbilityFactory.registerAbility(
				TrainWorkerAbility.class.getSimpleName(),
				new TrainWorkerAbility());
	}

	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(AbstractPlayerControlledEntity entity) {
		super.initialize(entity);
		this.setBuildCost(buildCost);
		this.setBuildTime(buildTime);
		this.setEntityToTrain(Worker.class.getSimpleName());
	}

	@Override
	public String getName() {
		return "Train Worker";
	}

	@Override
	public AbstractAbility createAbility(AbstractPlayerControlledEntity entity) {
		TrainWorkerAbility newAbility = new TrainWorkerAbility();
		newAbility.initialize(entity);
		return newAbility;
	}

	@Override
	public String getInfo() {
		return "Trains a new Worker\nCost: " + buildCost;
	}
}