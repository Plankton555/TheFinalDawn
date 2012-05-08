package projectrts.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Headquarter;
import projectrts.model.entities.Player;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.Warrior;
import projectrts.model.world.Position;


public class StrategicAITest {
	private Player aiPlayer;
	private Player humanPlayer;
	private GameModel model;
	private PlayerControlledEntity enemyWarrior;
	private PlayerControlledEntity myHQ;
	
	
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void testUpdate() {
		model = new GameModel();
		aiPlayer = (Player)model.getAIPlayer();
		humanPlayer = (Player)model.getHumanPlayer();
		EntityManager.getInstance().addNewPCE(Warrior.class.getSimpleName(), aiPlayer, new Position(1.5, 1.5));
		EntityManager.getInstance().addNewPCE(Headquarter.class.getSimpleName(), humanPlayer, new Position(1.5, 10.5));
		model.update(1f);
		enemyWarrior = EntityManager.getInstance().getPCEAtPosition(new Position(1.5, 1.5), aiPlayer);
		myHQ = EntityManager.getInstance().getPCEAtPosition(new Position(1.5, 10.5), humanPlayer);
		for(int i = 0; i < 500; i++) {
			model.update(0.5f);
		}
		assertTrue(enemyWarrior.getPosition().getX() != 1.5 || enemyWarrior.getPosition().getY() != 1.5);
		assertTrue(myHQ.isDead());


	}
}
