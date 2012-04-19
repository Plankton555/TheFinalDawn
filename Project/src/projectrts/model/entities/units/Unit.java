package projectrts.model.entities.units;

import projectrts.model.ai.MicroAI;
import projectrts.model.entities.AbstractUnit;
import projectrts.model.entities.EntityFactory;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.abilities.AbilityFactory;
import projectrts.model.entities.abilities.AttackAbility;
import projectrts.model.entities.abilities.MoveAbility;
import projectrts.model.entities.abilities.OffensiveSpellAbility;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;

/**
 * A simple unit.
 * @author Bjorn Persson Mattsson, Modified by Filip Brynfors
 *
 */
public class Unit extends AbstractUnit {

	// TODO Anyone: Remove this class later
	private Position targetPosition;
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
		this.stance = Stance.IDLE;
		abilities.add(AbilityFactory.INSTANCE.createAbility(AttackAbility.class.getSimpleName()));
		abilities.add(AbilityFactory.INSTANCE.createAbility(OffensiveSpellAbility.class.getSimpleName()));
		abilities.add(AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName()));
		this.setMaxHealth(20);
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
