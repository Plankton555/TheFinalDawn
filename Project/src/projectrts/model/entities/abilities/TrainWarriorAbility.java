package projectrts.model.entities.abilities;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.units.Warrior;
//TODO Jakob: Maybe extract common code with trainWorkerAbility to a abstract class
/**
 * A class that trains a Warrior
 * @author Jakob Svensson
 *
 */
public class TrainWarriorAbility extends AbstractTrainUnitAbility{
	private static float buildTime = 7; 
	private static int buildCost = 100; 
	private static float cooldown = 0.5f;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(TrainWarriorAbility.class.getSimpleName(), new TrainWarriorAbility());
	}
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize() {
		this.setCooldown(cooldown);
		this.setBuildCost(buildCost);
		this.setBuildTime(buildTime);
		this.setEntityName(Warrior.class.getSimpleName());
	}
	
	@Override
	public String getName() {
		return TrainWarriorAbility.class.getSimpleName();
	}

	
	@Override
	public AbstractAbility createAbility() {
		TrainWarriorAbility newAbility = new TrainWarriorAbility();
		newAbility.initialize();
		return newAbility;
	}

}
