package projectrts.model.core;

import java.util.List;

import projectrts.model.core.entities.*;
import projectrts.model.core.pathfinding.AStar;
import projectrts.model.core.pathfinding.World;

/**
 * The main model class of the RTS Game
 * The class handles the world and they players in the game
 * @author Björn Persson Mattson, Modified by Filip Brynfors, Jakob Svensson
 */
public class GameModel implements IGame {
	private World world = new World(P.INSTANCE.getWorldHeight(), P.INSTANCE.getWorldWidth());
	private EntityManager entityManager = EntityManager.getInstance();
	private Player humanPlayer = new Player();
	// TODO Plankton: Implement some sort of AI
	private Player aiPlayer = new Player();
	
	static {
		try
		{
			Class.forName(BasicUnit.class.getName());
			Class.forName(Unit.class.getName());
			Class.forName(Worker.class.getName());
			Class.forName(Resource.class.getName());
			Class.forName(Headquarter.class.getName());
		}
		catch (ClassNotFoundException any)
		{
			any.printStackTrace();
		}
    }
		
	public GameModel() {
		AStar.initialize(world);
		entityManager.addNewPCE("Unit", humanPlayer, new Position(50, 50));
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
	public List<IEntity> getAllEntities() {
		return entityManager.getAllEntities();
	}
}
