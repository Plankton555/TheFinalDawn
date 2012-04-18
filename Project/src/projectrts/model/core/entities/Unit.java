package projectrts.model.core.entities;

import projectrts.model.core.EntityFactory;
import projectrts.model.core.MicroAI;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.AbilityFactory;
import projectrts.model.core.abilities.AttackAbility;
import projectrts.model.core.abilities.MoveAbility;
import projectrts.model.core.abilities.OffensiveSpellAbility;

/**
 * A simple unit.
 * @author Bjorn Persson Mattsson, Modified by Filip Brynfors
 *
 */
public class Unit extends PlayerControlledEntity {

	// TODO Anyone: Remove this class later
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
		abilities.add(AbilityFactory.INSTANCE.createAbility(AttackAbility.class.getSimpleName()));
		abilities.add(AbilityFactory.INSTANCE.createAbility(OffensiveSpellAbility.class.getSimpleName()));
		abilities.add(AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName()));
		this.setMaxHealth(100);
		this.setCurrentHealth(this.getMaxHealth());
	}
	

	@Override
	public float getSize() {
		// Change Unit.getSize() later
		return 1;
		
	}
	
	@Override
	public float getSpeed() {
		// Change Unit.getSpeed() later
		return 3;
		
	}
	

	@Override
	public String getName() {
		//TODO Afton: Fix name
		return "Basic unit";
	}
	
	@Override
	public float getSightRange() {
		// TODO Afton: Change Unit.getSightRange() later
		return 10;
	}
	
	@Override
	public int getDamage() {
		return 10;
	}

	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		return new Unit(owner, pos);
	}


}
