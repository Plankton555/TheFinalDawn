package projectrts.model.entities.abilities;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.units.Warrior;
//TODO Jakob: Maybe extract common code with trainWorkerAbility to a abstract class
/**
 * A class that trains a Warrior
 * @author Jakob Svensson
 *
 */
public class TrainWarriorAbility extends AbstractCreationAbility implements INonMovableAbility {
	private static float buildTime = 7; 
	private static int buildCost = 100; 
	private static float cooldown = 0.5f;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(TrainWarriorAbility.class.getSimpleName(), new TrainWarriorAbility());
	}
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(PlayerControlledEntity entity) {
		this.setCooldown(cooldown);
		this.setBuildCost(buildCost);
		this.setBuildTime(buildTime);
		this.setEntity(entity);
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
