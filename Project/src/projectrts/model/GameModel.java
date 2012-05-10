package projectrts.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import projectrts.model.abilities.AbilityManager;
import projectrts.model.abilities.IAbilityManager;
import projectrts.model.entities.AbstractStructure;
import projectrts.model.entities.Archer;
import projectrts.model.entities.Barracks;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Headquarter;
import projectrts.model.entities.IEntity;
import projectrts.model.entities.IEntityManager;
import projectrts.model.entities.IPlayer;
import projectrts.model.entities.Player;
import projectrts.model.entities.Resource;
import projectrts.model.entities.Warrior;
import projectrts.model.entities.Worker;
import projectrts.model.world.INode;
import projectrts.model.world.IWorld;
import projectrts.model.world.Node;
import projectrts.model.world.Position;
import projectrts.model.world.World;

/**
 * The main model class of the RTS Game
 * The class handles the world and they players in the game
 * @author Björn Persson Mattson, Modified by Filip Brynfors, Jakob Svensson
 */
public class GameModel implements IGame, PropertyChangeListener {
	private final World world = World.getInstance();
	private final EntityManager entityManager = EntityManager.getInstance();
	private final Player humanPlayer = new Player();
	private final Player aiPlayer = new Player();
	private final AIManager aiManager;
	private AbilityManager abilityManager;
	private float gameTime = 0;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final Level level = new Level(aiPlayer);
	private Difficulty currentDifficulty = Difficulty.MEDIUM;
	
	/**
	 * Returns a position in the model with the given coordinates
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return A position with the given coordinates.
	 */
	public static Position getPosition(float x, float y) {
		// TODO Markus: Should this method exist or not?
		return new Position(x, y);
	}
	
	/**
	 * Determines whether any of the provided nodes are occupied.
	 * @param nodes The nodes to be examined.
	 * @return true if any node is occupied, otherwise false.
	 */
	@Override
	public boolean isAnyNodeOccupied(List<INode> nodes){
		return Node.isAnyNodeOccupied(nodes);
	}
	
	public GameModel() {
		world.initializeWorld();
		
		abilityManager = new AbilityManager();
		aiManager = new AIManager(aiPlayer, abilityManager);
		entityManager.resetData();
		entityManager.addListener(this);
		entityManager.addNewPCE(Warrior.class.getSimpleName(), humanPlayer, new Position(52.5, 52.5));
		entityManager.addNewPCE(Archer.class.getSimpleName(), humanPlayer, new Position(55.5, 52.5));
		entityManager.addNewPCE(Worker.class.getSimpleName(), humanPlayer, new Position(55.5, 55.5));
		entityManager.addNewPCE(Worker.class.getSimpleName(), humanPlayer, new Position(56.5, 55.5));
		entityManager.addNewPCE(Headquarter.class.getSimpleName(), humanPlayer, new Position(60.5, 60.5));
		entityManager.addNewPCE(Headquarter.class.getSimpleName(), humanPlayer, new Position(34.5, 50.5));
		entityManager.addNewPCE(Barracks.class.getSimpleName(), humanPlayer, new Position(38.5, 56.5));
		entityManager.addNewNPCE(Resource.class.getSimpleName(), new Position(40.5, 50.5));
		entityManager.addNewNPCE(Resource.class.getSimpleName(), new Position(40.5, 52.5));
		//entityManager.addNewPCE(Warrior.class.getSimpleName(), aiPlayer, new Position(32.5, 34.5));
		//entityManager.addNewPCE(Warrior.class.getSimpleName(), aiPlayer, new Position(30.5, 34.5));
		//entityManager.addNewPCE(Warrior.class.getSimpleName(), aiPlayer, new Position(28.5, 34.5));
		//entityManager.addNewPCE(Warrior.class.getSimpleName(), aiPlayer, new Position(26.5, 34.5));
		level.setDifficulty(Difficulty.NIGHTMARE);

	}
	
	@Override
	public void update(float tpf) {
		level.update(tpf);
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
	
	private void checkIfGameOver(){
		//Assumes that all structures are dead and loops until it finds a living one
		List<IEntity> entities = entityManager.getAllEntities();
		boolean allDead = true;
		for(IEntity entity: entities){
			if(entity instanceof AbstractStructure && !((AbstractStructure) entity).isDead()){
				allDead = false;
				break;
			}
		}
		if(allDead){
			setGameOver();
		}
	}
	
	private void setGameOver(){
		pcs.firePropertyChange("gameIsOver", false, true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if("entityRemoved".equals(event.getPropertyName())){
			checkIfGameOver();
		}
	}
	
	@Override
	public void setDifficulty(Difficulty difficulty) {
		currentDifficulty = difficulty;
		level.setDifficulty(currentDifficulty);
	}
	
	@Override
	public Difficulty getCurrentDifficulty() {
		return currentDifficulty;
	}
		
	@Override
	public void addListener(PropertyChangeListener pcl){
		pcs.addPropertyChangeListener(pcl);
	}


}
