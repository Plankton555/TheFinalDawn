package projectrts.model.abilities;

import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.Warrior;

/**
 * A class that trains a Warrior
 * 
 * @author Jakob Svensson
 * 
 */
class TrainWarriorAbility extends AbstractCreationAbility implements
		INotUsingMoveAbility {
	private static float buildTime = 7;
	private static int buildCost = 100;

	static {
		AbilityFactory.registerAbility(
				TrainWarriorAbility.class.getSimpleName(),
				new TrainWarriorAbility());
	}

	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(PlayerControlledEntity entity) {
		super.initialize(entity);
		this.setBuildCost(buildCost);
		this.setBuildTime(buildTime);
		this.setEntityToTrain(Warrior.class.getSimpleName());
	}

	@Override
	public String getName() {
		return "Train Warrior";
	}

	@Override
	public AbstractAbility createAbility(PlayerControlledEntity entity) {
		TrainWarriorAbility newAbility = new TrainWarriorAbility();
		newAbility.initialize(entity);
		return newAbility;
	}

	@Override
	public String getInfo() {
		return "Trains a new Warrior\nCost: " + buildCost;
	}
}