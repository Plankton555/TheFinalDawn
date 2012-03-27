package projectrts.model.core.entities;

import projectrts.model.core.EntityFactory;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.AttackAbility;

public class BasicUnit extends PlayerControlledEntity{

	static {
		EntityFactory.INSTANCE.registerPCE("BasicUnit", new BasicUnit(new Player(), new Position(0, 0)));
	}
	
	private BasicUnit(Player owner, Position spawnPos) {
		super(owner, spawnPos);
		this.abilities.add(new AttackAbility());
		setName("BasicUnit");
	}

	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		return new BasicUnit(owner, pos);
	}

}
