package projectrts.core.abilities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectrts.model.GameModel;
import projectrts.model.constants.P;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.abilities.AbilityFactory;
import projectrts.model.entities.abilities.GatherResourceAbility;
import projectrts.model.entities.misc.Resource;
import projectrts.model.entities.units.Worker;
import projectrts.model.player.Player;
import projectrts.model.utils.ModelUtils;
import projectrts.model.utils.Position;
/**
 * Test for GatherResourceAbility
 * @author Jakob Svensson
 *
 */
public class GatherResourceAbilityTest {

	@Test
	public void test() {
		new GameModel();
		GatherResourceAbility ab = (GatherResourceAbility) AbilityFactory.INSTANCE.createAbility(GatherResourceAbility.class.getSimpleName());
		Player player = new Player();
		EntityManager.getInstance().addNewPCE("Worker", player,new Position(1f,1f));
		EntityManager.getInstance().addNewPCE("Headquarter", player,new Position(10f,10f));
		EntityManager.getInstance().addNewPCE("Headquarter", player,new Position(20f,20f));
		EntityManager.getInstance().addNewNPCE("Resource", new Position(0f, 0f));
		EntityManager.getInstance().update(1);
		Worker worker = (Worker) EntityManager.getInstance().getPCEAtPosition(new Position(1f, 1f));
		Resource res = (Resource) EntityManager.getInstance().getNonPlayerControlledEntity(new Position(0f,0f));
		
		ab.useAbility(worker, res.getPosition());
		int counter = 0;

		while(player.getResource()!=P.INSTANCE.getWorkerCarryAmount()+P.INSTANCE.getResourceStarterAmount()){
			ab.update(1);
			counter++;
			assertTrue(counter < 1000);	
		}
		
		
		
	}

}
