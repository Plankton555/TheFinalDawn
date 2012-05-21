package projectrts.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import projectrts.model.abilities.AbilityManager;
import projectrts.model.abilities.IAbilityManager;
import projectrts.model.entities.AbstractStructure;
import projectrts.model.entities.Barracks;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Headquarter;
import projectrts.model.entities.IEntity;
import projectrts.model.entities.IEntityManager;
import projectrts.model.entities.IPlayer;
import projectrts.model.entities.Player;
import projectrts.model.entities.PlayerColor;
import projectrts.model.entities.Ranged;
import projectrts.model.entities.Resource;
import projectrts.model.entities.Warrior;
import projectrts.model.entities.Worker;
import projectrts.model.world.INode;
import projectrts.model.world.IWorld;
import projectrts.model.world.Node;
import projectrts.model.world.Position;
import projectrts.model.world.World;

/**
 * The main model class of the RTS Game The class handles the world and they
 * players in the game
 * 
 * @author Björn Persson Mattson, Modified by Filip Brynfors, Jakob Svensson
 */
public class GameModel implements IGame, PropertyChangeListener {
	private final World world = World.INSTANCE;
	private final EntityManager entityManager = EntityManager.INSTANCE;
	private final Player humanPlayer = new Player(PlayerColor.BLUE);
	private final Player aiPlayer = new Player(PlayerColor.RED);
	private final AIManager aiManager;
	private final AbilityManager abilityManager;
	private float gameTime = 0;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final WaveManager level = new WaveManager(aiPlayer);

	/**
	 * Determines whether any of the provided nodes are occupied.
	 * 
	 * @param nodes
	 *            The nodes to be examined.
	 * @return true if any node is occupied, otherwise false.
	 */
	@Override
	public boolean isAnyNodeOccupied(List<INode> nodes) {
		return Node.isAnyNodeOccupied(nodes);
	}

	/**
	 * Creates a new GameModel.
	 */
	public GameModel() {
		world.initializeWorld();

		abilityManager = new AbilityManager();
		aiManager = new AIManager(aiPlayer, abilityManager);
		entityManager.resetData();
		entityManager.addListener(this);

		// Place starting units
		entityManager.addNewPCE(Warrior.class.getSimpleName(), humanPlayer,
				new Position(50.5, 47.5));
		entityManager.addNewPCE(Ranged.class.getSimpleName(), humanPlayer,
				new Position(50.5, 48.5));
		entityManager.addNewPCE(Worker.class.getSimpleName(), humanPlayer,
				new Position(50.5, 50.5));
		entityManager.addNewPCE(Worker.class.getSimpleName(), humanPlayer,
				new Position(50.5, 51.5));
		entityManager.addNewPCE(Worker.class.getSimpleName(), humanPlayer,
				new Position(50.5, 52.5));
		entityManager.addNewPCE(Headquarter.class.getSimpleName(), humanPlayer,
				new Position(46.5, 53.5));
		entityManager.addNewPCE(Barracks.class.getSimpleName(), humanPlayer,
				new Position(47.5, 46.5));
		entityManager.addNewNPCE(Resource.class.getSimpleName(), new Position(
				54.5, 55.5));
		entityManager.addNewNPCE(Resource.class.getSimpleName(), new Position(
				54.5, 53.5));
		entityManager.addNewNPCE(Resource.class.getSimpleName(), new Position(
				54.5, 51.5));

	}

	@Override
	public void update(float tpf) {
		level.update(tpf);
		aiManager.update(tpf);
		abilityManager.update(tpf);
		entityManager.update(tpf);
		gameTime += tpf;
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
		return EntityManager.INSTANCE;
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

	private void checkIfGameOver() {
		// Assumes that all structures are dead and loops until it finds a
		// living one
		List<IEntity> entities = entityManager.getAllEntities();
		boolean allDead = true;
		for (IEntity entity : entities) {
			if (entity instanceof AbstractStructure
					&& !((AbstractStructure) entity).isDead()) {
				allDead = false;
				break;
			}
		}
		if (allDead) {
			setGameOver();
		}
	}

	private void setGameOver() {
		pcs.firePropertyChange("gameIsOver", false, true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if ("entityRemoved".equals(event.getPropertyName())) {
			checkIfGameOver();
		}
	}

	@Override
	public void setDifficulty(AbstractDifficulty difficulty) {
		level.setDifficulty(difficulty);
	}

	@Override
	public AbstractDifficulty getCurrentDifficulty() {
		return level.getDifficulty();
	}

	@Override
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
}
