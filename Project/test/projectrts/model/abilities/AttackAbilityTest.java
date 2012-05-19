package projectrts.model.abilities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectrts.model.GameModel;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Player;
import projectrts.model.entities.Warrior;
import projectrts.model.entities.Worker;
import projectrts.model.world.Position;

public class AttackAbilityTest {

	@Test
	public void test() {
		new GameModel();

		Player player1 = new Player();
		Player player2 = new Player();
		Position warriorSpawnPos = new Position(2.5f, 15.5f);
		Position workerSpawnPos = new Position(5.5f, 15.5f);

		EntityManager.INSTANCE.addNewPCE(Warrior.class.getSimpleName(),
				player1, warriorSpawnPos);
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player2,
				workerSpawnPos);
		EntityManager.INSTANCE.update(1);

		Warrior warrior = (Warrior) EntityManager.INSTANCE
				.getPCEAtPosition(warriorSpawnPos);
		Worker worker = (Worker) EntityManager.INSTANCE
				.getPCEAtPosition(workerSpawnPos);

		MoveAbility move = new MoveAbility();
		move.initialize(warrior);

		AttackAbility ab = (AttackAbility) AbilityFactory
				.createUsingMoveAbility(AttackAbility.class.getSimpleName(),
						warrior, move);
		ab.useAbility(worker.getPosition());

		int counter = 0;

		while (!worker.isDead()) {
			ab.update(.5f);
			move.update(.5f);

			counter++;
			assertTrue(counter < 10000);
		}

	}

}
