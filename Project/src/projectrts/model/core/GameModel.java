package projectrts.model.core;

import java.util.List;

import projectrts.model.core.entities.IEntity;
import projectrts.model.core.entities.IPlayerControlledEntity;
import projectrts.model.core.entities.Unit;

/**
 * The main model class of the RTS Game
 * The class handles the world and they players in the game
 * @author Björn Persson Mattson, Modified by Filip Brynfors
 */
public class GameModel implements IGame {
	private World world = new World(P.INSTANCE.getWorldHeight(), P.INSTANCE.getWorldWidth());
	private EntityManager entityManager = EntityManager.getInstance();
	private Player humanPlayer = new Player();
	private Player aiPlayer = new Player();
	
	public GameModel() {
		entityManager.addEntity(new Unit(new Position(50, 50), humanPlayer));
	}
	
	
	@Override
	public void update(float tpf) {
		entityManager.update(tpf);
	}

	@Override
	public IPlayer getPlayer() {
		return humanPlayer;
	}

	@Override
	public ITile[][] getTileMap() {
		return world.getTileMap();
	}

	@Override
	public List<IEntity> getAllEntities() {
		return entityManager.getAllEntities();
	}
}
