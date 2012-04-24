package projectrts.model.entities;


import projectrts.model.player.Player;
import projectrts.model.utils.Position;
//TODO Afton: ADD JAVADOC!!
public abstract class AbstractStructure extends PlayerControlledEntity{
	protected boolean deposit = false;
	
	/**
	 * When subclassing, invoke this to initialize the entity.
	 * @param owner The owner of the entity.
	 * @param spawnPos The initial position of the entity.
	 */
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
	}
	
	public boolean isDeposit() {
		return deposit;
	}
}
