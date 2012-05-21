package projectrts.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectrts.model.abilities.AbilityManager;
import projectrts.model.entities.AbstractPlayerControlledEntity;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Headquarter;
import projectrts.model.entities.Player;
import projectrts.model.entities.Warrior;
import projectrts.model.world.Position;

public class StrategicAITest {
	private Player aiPlayer;
	private Player humanPlayer;
	private GameModel model;
	private AbstractPlayerControlledEntity enemyWarrior;
	private AbstractPlayerControlledEntity myHQ;
	private EntityManager entityManager = EntityManager.INSTANCE;
	private AbilityManager abilityManager;
	private AIManager aiManager;

	@Test
	public void testUpdate() {
		model = new GameModel();
		aiPlayer = (Player) model.getAIPlayer();
		humanPlayer = (Player) model.getHumanPlayer();
		abilityManager = new AbilityManager();
		aiManager = new AIManager(aiPlayer, abilityManager);
		entityManager.resetData();
		entityManager.addNewPCE(Warrior.class.getSimpleName(),
				aiPlayer, new Position(1.5, 1.5));
		entityManager.addNewPCE(Headquarter.class.getSimpleName(),
				humanPlayer, new Position(1.5, 10.5));
		model.update(1f);
		enemyWarrior = entityManager.getPCEAtPosition(new Position(
				1.5, 1.5), aiPlayer);
		myHQ = entityManager.getPCEAtPosition(new Position(1.5, 10.5),
				humanPlayer);
		
		float updateInterval = 0.2f;
		boolean done = false;
		for (int i = 0; i < 2000; i++) {
			aiManager.update(updateInterval);
			abilityManager.update(updateInterval);
			entityManager.update(updateInterval);
			if ((enemyWarrior.getPosition().getX() != 1.5
					|| enemyWarrior.getPosition().getY() != 1.5)
					&& myHQ.isDead()) {
				done = true;
				break;
			}
		}
		assertTrue(done);
	}
}