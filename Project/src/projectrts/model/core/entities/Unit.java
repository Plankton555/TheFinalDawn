package projectrts.model.core.entities;

import projectrts.model.core.MicroAI;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.AttackAbility;

/**
 * A simple unit.
 * @author Bjorn Persson Mattsson, Modified by Filip Brynfors
 *
 */
public class Unit extends PlayerControlledEntity {

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
	 * @param owner The owner of the unit
	 */
	public Unit(Position spawnPos, Player owner)
	{
		super(spawnPos, owner, 100);
		this.microAI = new MicroAI(this);
		this.stance = Stance.IDLE;
		abilities.add(new AttackAbility());
	}
	

	@Override
	public float getSize() {
		// TODO Change this later
		return 1;
	}

	
	/**
	 * Updates the unit.
	 * @param tpf Time per frame
	 */
	@Override
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

	@Override
	public String getName() {
		//TODO: Fix name
		return "Basic unit";
	}


	@Override
	public float getSightRange() {
		// TODO Change this later
		return 10;
	}
}