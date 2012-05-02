package projectrts.model.abilities;

import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.Warrior;
//TODO Jakob: Maybe extract common code with trainWorkerAbility to a abstract class
/**
 * A class that trains a Warrior
 * @author Jakob Svensson
 *
 */
public class TrainWarriorAbility extends AbstractCreationAbility implements INotUsingMoveAbility {
	private static float buildTime = 7; 
	private static int buildCost = 100; 
	
	static {
		AbilityFactory.INSTANCE.registerAbility(TrainWarriorAbility.class.getSimpleName(), new TrainWarriorAbility());
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
		return TrainWarriorAbility.class.getSimpleName();
	}

	
	@Override
	public AbstractAbility createAbility(PlayerControlledEntity entity) {
		TrainWarriorAbility newAbility = new TrainWarriorAbility();
		newAbility.initialize(entity);
		return newAbility;
	}

}
