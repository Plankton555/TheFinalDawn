package projectrts.model.entities.abilities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectrts.model.GameModel;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.abilities.AbilityFactory;
import projectrts.model.entities.abilities.DeliverResourceAbility;
import projectrts.model.entities.units.Worker;
import projectrts.model.player.Player;
import projectrts.model.utils.ModelUtils;
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
		DeliverResourceAbility ab = (DeliverResourceAbility)AbilityFactory.INSTANCE.createAbility(DeliverResourceAbility.class.getSimpleName());
		Player player = new Player();
		EntityManager.getInstance().addNewPCE("Worker", player,new Position(1f,1f));
		EntityManager.getInstance().addNewPCE("Headquarter", player,new Position(5f,5f));
		EntityManager.getInstance().addNewNPCE("Resource", new Position(0f, 0f));
		EntityManager.getInstance().update(1);
		Worker worker = (Worker)  EntityManager.getInstance().getPCEAtPosition(new Position(1f, 1f));
		
		ab.useAbility(worker, new Position(0, 0));
		
		int counter = 0;
		while(!ab.isFinished()){
			
			ab.update(1);
			counter++;
			assertTrue(counter < 1000);	
		}
	}

}