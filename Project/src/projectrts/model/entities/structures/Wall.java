package projectrts.model.entities.structures;

import projectrts.model.entities.AbstractStructure;
import projectrts.model.entities.EntityFactory;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;

/**
 * A building for blocking spaces 
 * @author Jakob Svensson
 *
 */
public class Wall extends AbstractStructure{
	
private static float size = 1;
private static final float sightRange = 5;
private static int maxHealth = 500;
	
	static {
		EntityFactory.INSTANCE.registerPCE(Wall.class.getSimpleName(), new Wall());
	}
	
	protected void initialize(Player owner, Position spawnPos) {
		super.initialize(owner, spawnPos);
		this.setName(Wall.class.getSimpleName());
		this.setSize(size);
		this.setSightRange(sightRange);
		this.setMaxHealth(maxHealth);		
		deposit = false;
	}
	
	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		Wall newBarracks = new Wall();
		newBarracks.initialize(owner, pos);
		return newBarracks;
	}

}