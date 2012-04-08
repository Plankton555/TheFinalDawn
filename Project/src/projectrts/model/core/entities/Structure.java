package projectrts.model.core.entities;



import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.BuildTowerAbility;
import projectrts.model.core.abilities.IAbility;



/**
 * A simple Structure
 * @author Filip Brynfors
 *
 */
public class Structure extends PlayerControlledEntity {
	
	/**
	 * Spawns a Structure at the provided position.
	 * @param spawnPos Spawn position
	 */
	public Structure(Position spawnPos, Player owner){
		super(owner, spawnPos);
		abilities.add(new BuildTowerAbility());
	}
	
	@Override
	public float getSize() {
		// TODO Afton: Change Structure.getSize() later
		return 1;
	}

	@Override
	public void update(float tpf){
		// TODO Afton: Add a micro AI for attacking the structure has offensive abilities
	}

	@Override
	public String getName() {
		// TODO Afton: Change name in Structure.getName()
		return "Basic Structure";
	}

	@Override
	public float getSightRange() {
		// TODO Afton: Change Structure.getSightRange() later
		return 10;
	}

	@Override
	public PlayerControlledEntity createPCE(Player owner, Position pos) {
		// TODO Afton: Implement Structure.createPCE()
		return null;
	}
	
}
