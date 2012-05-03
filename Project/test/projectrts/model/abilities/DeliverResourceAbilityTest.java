package projectrts.model.abilities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectrts.model.GameModel;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Worker;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;
/**
 * Test for DeliverResourceAbility
 * @author Jakob Svensson
 *
 */
public class DeliverResourceAbilityTest {

	@Test
	public void test() {
		new GameModel();
		
		Player player = new Player();
		EntityManager.getInstance().addNewPCE("Worker", player,new Position(1f,1f));
		EntityManager.getInstance().addNewPCE("Headquarter", player,new Position(5f,5f));
		EntityManager.getInstance().addNewNPCE("Resource", new Position(0f, 0f));
		EntityManager.getInstance().update(1);
		Worker worker = (Worker)  EntityManager.getInstance().getPCEAtPosition(new Position(1f, 1f));
		MoveAbility move = new MoveAbility();
		move.initialize(worker);
		DeliverResourceAbility ab = (DeliverResourceAbility)AbilityFactory.INSTANCE.createMAbility(DeliverResourceAbility.class.getSimpleName(),worker, move);
		
		ab.useAbility(new Position(0, 0));
		
		int counter = 0;
		while(!ab.isFinished()){
			move.update(1);
			ab.update(1);
			counter++;
			assertTrue(counter < 1000);	
		}
	}

}
