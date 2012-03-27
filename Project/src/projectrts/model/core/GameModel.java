package projectrts.model.core;

import java.util.List;

import projectrts.model.core.entities.*;

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
	
	static {
		try
		{
			Class.forName("projectrts.model.core.entities.BasicUnit");
		}
		catch (ClassNotFoundException any)
		{
			any.printStackTrace();
		}
    }
		
	public GameModel() {
		entityManager.addEntity(new Unit(new Position(50, 50), humanPlayer));
		PlayerControlledEntity basicUnit = EntityFactory.INSTANCE.createPCE("BasicUnit", humanPlayer, new Position(35, 35));
		System.out.println(basicUnit.getName());
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
