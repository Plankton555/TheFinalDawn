package projectrts.model.entities.abilities;

import projectrts.model.entities.AbstractAbility;
import projectrts.model.entities.units.Worker;

/**
 * A class that trains a unit
 * @author Jakob Svensson
 *
 */
public class TrainWorkerAbility extends AbstractTrainUnitAbility{
	private static float buildTime = 5; 
	private static int buildCost = 50; 
	private static float cooldown = .5f;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(TrainWorkerAbility.class.getSimpleName(), new TrainWorkerAbility());
	}
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize() {
		this.setCooldown(cooldown);
		this.setBuildCost(buildCost);
		this.setBuildTime(buildTime);
		this.setEntityName(Worker.class.getSimpleName());
	}
	
	@Override
	public String getName() {
		return "TrainWorker";
	}


	@Override
	public AbstractAbility createAbility() {
		TrainWorkerAbility newAbility = new TrainWorkerAbility();
		newAbility.initialize();
		return newAbility;
	}

}
