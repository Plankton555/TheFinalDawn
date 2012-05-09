package projectrts.model.abilities;

import projectrts.model.entities.Archer;
import projectrts.model.entities.PlayerControlledEntity;

/**
 * A class that trains an Acrher
 * @author Jakob Svensson
 *
 */
public class TrainArcherAbility extends AbstractCreationAbility implements INotUsingMoveAbility {
	private static float buildTime = 8; 
	private static int buildCost = 150; 
	
	static {
		AbilityFactory.INSTANCE.registerAbility(TrainArcherAbility.class.getSimpleName(), new TrainArcherAbility());
	}
	
	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(PlayerControlledEntity entity) {
		super.initialize(entity);
		this.setBuildCost(buildCost);
		this.setBuildTime(buildTime);
		this.setEntityToTrain(Archer.class.getSimpleName());
	}
	
	@Override
	public String getName() {
		return TrainArcherAbility.class.getSimpleName();
	}

	
	@Override
	public AbstractAbility createAbility(PlayerControlledEntity entity) {
		TrainArcherAbility newAbility = new TrainArcherAbility();
		newAbility.initialize(entity);
		return newAbility;
	}

}
