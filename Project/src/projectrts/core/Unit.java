package projectrts.core;

/**
 * A simple unit.
 * @author Bjorn Persson Mattsson
 *
 */
public class Unit implements IUnit {

	private Player owner;
	private Position position;
	private Position targetPosition;
	private MicroAI microAI;
	private Stance stance;
	
	private enum Stance
	{
		IDLE, MOVING;
	}
	
	/**
	 * Spawns a unit at the provided position.
	 * @param spawnPos Spawn position
	 */
	public Unit(Position spawnPos, Player owner)
	{
		this.position = new Position(spawnPos);
		this.owner = owner;
		this.microAI = new MicroAI(this);
		this.stance = Stance.IDLE;
	}
	
	@Override
	public Position getPosition() {
		return this.position;
	}

	@Override
	public float getSize() {
		// TODO Change this later
		return 1;
	}

	@Override
	public IPlayer getOwner() {
		return owner;
	}
	
	/**
	 * Updates the unit.
	 * @param tpf Time per frame
	 */
	public void update(float tpf)
	{
		switch (stance)
		{
		case IDLE:
			// Do nothing atm
			break;
			
		case MOVING:
			this.position = microAI.determinePath(targetPosition, tpf);
			
			if (this.position.equals(targetPosition))
			{
				// if on target position, stop
				stance = Stance.IDLE;
			}
			break;
		}
	}
	
	/**
	 * Orders the unit to move to the provided position.
	 * @param p Target position
	 */
	public void moveTo(Position p)
	{
		targetPosition = new Position(p);
		stance = Stance.MOVING;
	}
}
