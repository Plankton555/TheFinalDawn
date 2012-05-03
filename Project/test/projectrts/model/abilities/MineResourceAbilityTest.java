package projectrts.model.abilities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectrts.model.GameModel;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Resource;
import projectrts.model.entities.Worker;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;
/**
 * Test for MineResourceAbility
 * @author Jakob Svensson
 *
 */

public class MineResourceAbilityTest {

	@Test
	public void test() {
		new GameModel();
		
		Player player = new Player();
		EntityManager.getInstance().addNewPCE("Worker", player,new Position(1f,1f));
		EntityManager.getInstance().addNewNPCE("Resource", new Position(5f, 5f));
		EntityManager.getInstance().update(1);
		Worker worker = (Worker)  EntityManager.getInstance().getPCEAtPosition(new Position(1f, 1f));
		Resource res = (Resource) EntityManager.getInstance().getNonPlayerControlledEntity(new Position(5f,5f));
		MineResourceAbility ab = (MineResourceAbility)AbilityFactory.INSTANCE.createAbility(MineResourceAbility.class.getSimpleName(),worker);
		ab.useAbility(res.getPosition());
		int counter = 0;
		while(!ab.isFinished()){
			
			ab.update(1);
			counter++;
			assertTrue(counter < 1000);	
		}
	}

}
