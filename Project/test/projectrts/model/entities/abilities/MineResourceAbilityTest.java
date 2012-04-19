package projectrts.model.entities.abilities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectrts.model.GameModel;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.abilities.AbilityFactory;
import projectrts.model.entities.abilities.MineResourceAbility;
import projectrts.model.entities.misc.Resource;
import projectrts.model.entities.units.Worker;
import projectrts.model.player.Player;
import projectrts.model.utils.ModelUtils;
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
		MineResourceAbility ab = (MineResourceAbility)AbilityFactory.INSTANCE.createAbility(MineResourceAbility.class.getSimpleName());
		Player player = new Player();
		EntityManager.getInstance().addNewPCE("Worker", player,new Position(1f,1f));
		EntityManager.getInstance().addNewNPCE("Resource", new Position(5f, 5f));
		EntityManager.getInstance().update(1);
		Worker worker = (Worker)  EntityManager.getInstance().getPCEAtPosition(new Position(1f, 1f));
		Resource res = (Resource) EntityManager.getInstance().getNonPlayerControlledEntity(new Position(5f,5f));
		
		ab.useAbility(worker, res.getPosition());
		int counter = 0;
		while(!ab.isFinished()){
			
			ab.update(1);
			counter++;
			assertTrue(counter < 1000);	
		}
	}

}
