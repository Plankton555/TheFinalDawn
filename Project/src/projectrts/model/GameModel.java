package projectrts.model;

import projectrts.model.abilities.AbilityManager;
import projectrts.model.abilities.IAbilityManager;
import projectrts.model.ai.AIManager;
import projectrts.model.entities.Barracks;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Headquarter;
import projectrts.model.entities.IEntityManager;
import projectrts.model.entities.Resource;
import projectrts.model.entities.Warrior;
import projectrts.model.entities.Worker;
import projectrts.model.pathfinding.AStar;
import projectrts.model.player.IPlayer;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;
import projectrts.model.world.IWorld;
import projectrts.model.world.World;

/**
 * The main model class of the RTS Game
 * The class handles the world and they players in the game
 * @author Björn Persson Mattson, Modified by Filip Brynfors, Jakob Svensson
 */
public class GameModel implements IGame {
	private World world = World.getInstance();
	private EntityManager entityManager = EntityManager.getInstance();
	private Player humanPlayer = new Player();
	private Player aiPlayer = new Player();
	private AIManager aiManager;
	private AbilityManager abilityManager;
	private float gameTime = 0;
	
	public GameModel() {
		world.initializeWorld();
		AStar.initialize(world);
		abilityManager = new AbilityManager();
		aiManager = new AIManager(aiPlayer, abilityManager);
		entityManager.addNewPCE(Warrior.class.getSimpleName(), humanPlayer, new Position(52.5, 52.5));
		entityManager.addNewPCE(Worker.class.getSimpleName(), humanPlayer, new Position(55.5, 55.5));
		entityManager.addNewPCE(Worker.class.getSimpleName(), humanPlayer, new Position(56.5, 55.5));
		entityManager.addNewPCE(Headquarter.class.getSimpleName(), humanPlayer, new Position(60.5, 60.5));
		entityManager.addNewPCE(Headquarter.class.getSimpleName(), humanPlayer, new Position(34.5, 50.5));
		entityManager.addNewPCE(Barracks.class.getSimpleName(), humanPlayer, new Position(38.5, 56.5));
		entityManager.addNewNPCE(Resource.class.getSimpleName(), new Position(40.5, 50.5));
		entityManager.addNewNPCE(Resource.class.getSimpleName(), new Position(40.5, 52.5));
		entityManager.addNewPCE(Warrior.class.getSimpleName(), aiPlayer, new Position(32.5, 34.5));


	}
	
	@Override
	public void update(float tpf) {
		aiManager.update(tpf);
		abilityManager.update(tpf);
		entityManager.update(tpf);
		gameTime +=tpf;
	}

	@Override
	public IPlayer getHumanPlayer() {
		return humanPlayer;
	}
	
	@Override
	public IPlayer getAIPlayer() {
		return aiPlayer;
	}

	@Override
	public IEntityManager getEntityManager() {
		return EntityManager.getInstance();
	}
	
	@Override
	public IAbilityManager getAbilityManager() {
		return abilityManager;
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
