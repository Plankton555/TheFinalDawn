package projectrts.model.core.entities;

import projectrts.model.core.EntityFactory;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.AttackAbility;

/**
 *  An example of a concrete unit and it's methods.
 * @author Markus Ekström
 *
 */
public class Warrior extends PlayerControlledEntity{
	
	static {
		EntityFactory.INSTANCE.registerPCE(Warrior.class.getSimpleName(), new Warrior());
	}
	
	private Warrior() {
	}
	
	private Warrior(Player owner, Position spawnPos) {
		super(owner, spawnPos);
		this.abilities.add(new AttackAbility());
		setName(Warrior.class.getSimpleName());
	}

	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		return new Warrior(owner, pos);
	}

	@Override
	public float getSightRange() {
		return 5;
	}

}
