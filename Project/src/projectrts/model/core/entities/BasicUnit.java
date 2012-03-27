package projectrts.model.core.entities;

import projectrts.model.core.EntityFactory;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.AttackAbility;

public class BasicUnit extends PlayerControlledEntity{

	private static String name = "BasicUnit";
	
	static {
		EntityFactory.INSTANCE.registerPCE(name, new BasicUnit());
	}
	
	private BasicUnit() {
	}
	
	private BasicUnit(Player owner, Position spawnPos) {
		super(owner, spawnPos);
		this.abilities.add(new AttackAbility());
		setName(name);
	}

	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		return new BasicUnit(owner, pos);
	}

}
