package projectrts.model.core.entities;

import projectrts.model.core.EntityFactory;
import projectrts.model.core.MicroAI;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.AttackAbility;
import projectrts.model.core.abilities.MoveAbility;
import projectrts.model.core.abilities.OffensiveSpellAbility;

/**
 * A simple unit.
 * @author Bjorn Persson Mattsson, Modified by Filip Brynfors
 *
 */
public class Unit extends PlayerControlledEntity {

	private Position targetPosition;
	private MicroAI microAI;
	private Stance stance;
	private static final String name = "Unit";

	static {
		EntityFactory.INSTANCE.registerPCE(name, new Unit());
	}
	
	private enum Stance
	{
		IDLE, MOVING;
	}
	
	private Unit() {}
	
	/**
	 * Spawns a unit at the provided position.
	 * @param spawnPos Spawn position
	 * @param owner The owner of the unit
	 */
	private Unit(Player owner, Position spawnPos)
	{
		super(owner, spawnPos);
		this.microAI = new MicroAI(this);
		this.stance = Stance.IDLE;
		abilities.add(new AttackAbility());
		abilities.add(new OffensiveSpellAbility());
		abilities.add(new MoveAbility());
	}
	

	@Override
	public float getSize() {
		// TODO Change this later
		return 1;
		
	}


	/**
	 * Updates the unit.
	 * @param tpf Time per frame
	 **/
	/*
	@Override
	public void update(float tpf)
	{
		switch (stance)
		{
		case IDLE:
			// Do nothing atm
			break;
			
		case MOVING:
			Position oldPos = this.position;
			this.position = microAI.determinePath(targetPosition, tpf);
			if (this.position.equals(targetPosition))
			{
				// if on target position, stop
				stance = Stance.IDLE;
			}
			break;
		}
	}
	

	*/
	
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


	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		return new Unit(owner, pos);
	}
}
