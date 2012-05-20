package projectrts.model.abilities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectrts.model.GameModel;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Player;
import projectrts.model.entities.Worker;
import projectrts.model.world.Position;

/**
 * A test class for testing TrainWorkerAbility
 * 
 * @author Jakob Svensson
 * 
 */
public class BuildHeadquarterAbilityTest {

	@Test
	public void test() {
		new GameModel();
		Player player = new Player();
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(10.5f, 10.5f));
		EntityManager.INSTANCE.update(1);
		Worker worker = (Worker) EntityManager.INSTANCE
				.getPCEAtPosition(new Position(10.5f, 10.5f));
		MoveAbility move = new MoveAbility();
		move.initialize(worker);
		BuildHeadquarterAbility ab = (BuildHeadquarterAbility) AbilityFactory
				.createMoveableAbility(
						BuildHeadquarterAbility.class.getSimpleName(), worker,
						move);

		ab.useAbility(new Position(5.5, 3.5));
		int counter = 0;
		while (!ab.isFinished()
				|| EntityManager.INSTANCE.getPCEAtPosition(new Position(5.5,
						3.5)) == null) {
			ab.update(2);
			move.update(2);
			counter++;
			EntityManager.INSTANCE.update(1);
			assertTrue(counter < 100);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
