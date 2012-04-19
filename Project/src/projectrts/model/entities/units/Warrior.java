package projectrts.model.entities.units;

import projectrts.model.entities.AbstractUnit;
import projectrts.model.entities.EntityFactory;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.abilities.AbilityFactory;
import projectrts.model.entities.abilities.AttackAbility;
import projectrts.model.entities.abilities.MoveAbility;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;

/**
 *  An example of a concrete unit and it's methods.
 * @author Markus Ekström
 *
 */
public class Warrior extends AbstractUnit{
	
	static {
		EntityFactory.INSTANCE.registerPCE(Warrior.class.getSimpleName(), new Warrior());
	}
	
	private Warrior() {
	}
	
	private Warrior(Player owner, Position spawnPos) {
		super(owner, spawnPos);
		this.abilities.add(AbilityFactory.INSTANCE.createAbility(AttackAbility.class.getSimpleName()));
		this.abilities.add(AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName()));
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

	@Override
	public int getDamage() {
		return 20;
	}

}
