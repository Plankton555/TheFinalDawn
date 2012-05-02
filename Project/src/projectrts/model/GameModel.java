package projectrts.model;

import projectrts.model.constants.P;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.IEntityManager;
import projectrts.model.entities.abilities.AttackAbility;
import projectrts.model.entities.abilities.BuildBarracksAbility;
import projectrts.model.entities.abilities.BuildWallAbility;
import projectrts.model.entities.abilities.DeliverResourceAbility;
import projectrts.model.entities.abilities.GatherResourceAbility;
import projectrts.model.entities.abilities.MineResourceAbility;
import projectrts.model.entities.abilities.MoveAbility;
import projectrts.model.entities.abilities.OffensiveSpellAbility;
import projectrts.model.entities.abilities.TrainWarriorAbility;
import projectrts.model.entities.abilities.TrainWorkerAbility;
import projectrts.model.entities.misc.Resource;
import projectrts.model.entities.structures.Barracks;
import projectrts.model.entities.structures.Headquarter;
import projectrts.model.entities.structures.Wall;
import projectrts.model.entities.units.Warrior;
import projectrts.model.entities.units.Worker;
import projectrts.model.pathfinding.IWorld;
import projectrts.model.pathfinding.World;
import projectrts.model.player.IPlayer;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;

/**
 * The main model class of the RTS Game
 * The class handles the world and they players in the game
 * @author Björn Persson Mattson, Modified by Filip Brynfors, Jakob Svensson
 */
public class GameModel implements IGame {
	private World world = World.getInstance();
	private EntityManager entityManager = EntityManager.getInstance();
	private Player humanPlayer = new Player();
	// Depending on who gets to it first:
	// TODO Markus: Implement some sort of AI
	private Player aiPlayer = new Player();
	private float gameTime = 0;
	
	static {
		try
		{
			// Initialize the entity classes.
			Class.forName(Warrior.class.getName());
			Class.forName(Worker.class.getName());
			Class.forName(Resource.class.getName());
			Class.forName(Headquarter.class.getName());
			Class.forName(Barracks.class.getName());
			Class.forName(Wall.class.getName());
			
			// Initialize the ability classes.
			Class.forName(AttackAbility.class.getName());
			Class.forName(BuildBarracksAbility.class.getName());
			Class.forName(BuildWallAbility.class.getName());	
			Class.forName(DeliverResourceAbility.class.getName());
			Class.forName(GatherResourceAbility.class.getName());
			Class.forName(MineResourceAbility.class.getName());
			Class.forName(MoveAbility.class.getName());
			Class.forName(OffensiveSpellAbility.class.getName());
			Class.forName(TrainWorkerAbility.class.getName());
			Class.forName(TrainWarriorAbility.class.getName());
						
		}
		catch (ClassNotFoundException any)
		{
			any.printStackTrace();
		}
    }
		
	public GameModel() {
		world.initializeWorld();
		entityManager.addNewPCE(Warrior.class.getSimpleName(), humanPlayer, new Position(32.5, 32.5));
		//entityManager.addNewPCE(Worker.class.getSimpleName(), humanPlayer, new Position(55.5, 55.5));
		//entityManager.addNewPCE(Worker.class.getSimpleName(), humanPlayer, new Position(56.5, 55.5));
		entityManager.addNewPCE(Headquarter.class.getSimpleName(), humanPlayer, new Position(60.5, 60.5));
		//entityManager.addNewPCE(Headquarter.class.getSimpleName(), humanPlayer, new Position(34.5, 50.5));
		entityManager.addNewPCE(Barracks.class.getSimpleName(), humanPlayer, new Position(38.5, 56.5));
		entityManager.addNewNPCE(Resource.class.getSimpleName(), new Position(40.5, 50.5));
		entityManager.addNewNPCE(Resource.class.getSimpleName(), new Position(40.5, 52.5));
		entityManager.addNewPCE(Warrior.class.getSimpleName(), aiPlayer, new Position(32.5, 34.5));
		


	}
	
	@Override
	public void update(float tpf) {
		entityManager.update(tpf);
		gameTime +=tpf;
	}

	@Override
	public IPlayer getPlayer() {
		return humanPlayer;
	}

	@Override
	public IEntityManager getEntityManager() {
		return EntityManager.getInstance();
	}

	@Override
	public IWorld getWorld() {
		return world;
	}

	@Override
	public float getGameTime() {
		return gameTime;
	}
}
