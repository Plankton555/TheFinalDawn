package projectrts.core.abilities;

import static org.junit.Assert.*;

import org.junit.Test;

import projectrts.model.core.EntityManager;
import projectrts.model.core.GameModel;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.DeliverResourceAbility;
import projectrts.model.core.entities.Headquarter;
import projectrts.model.core.entities.Worker;
import projectrts.model.core.utils.ModelUtils;
/**
 * Test for DeliverResourceAbility
 * @author Jakob Svensson
 *
 */
public class DeliverResourceAbilityTest {

	@Test
	public void test() {
		new GameModel();
		DeliverResourceAbility ab = new DeliverResourceAbility();
		Player player = new Player();
		EntityManager.getInstance().addNewPCE("Worker", player,new Position(1f,1f));
		EntityManager.getInstance().addNewPCE("Headquarter", player,new Position(5f,5f));
		Worker worker = (Worker) ModelUtils.INSTANCE.getPlayerControlledEntityAtPosition(new Position(1f, 1f));
		
		ab.useAbility(worker, new Position(0, 0));
		ab.setResourceCarriedAmount(10);
		int counter = 0;
		while(player.getResource()!=10){
			
			ab.update(1);
			counter++;
			assertTrue(counter < 1000);	
		}
	}

}
