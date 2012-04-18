package projectrts.core.abilities;

import static org.junit.Assert.*;

import org.junit.Test;

import projectrts.model.core.EntityManager;
import projectrts.model.core.GameModel;
import projectrts.model.core.P;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.AbilityFactory;
import projectrts.model.core.abilities.DeliverResourceAbility;
import projectrts.model.core.abilities.GatherResourceAbility;
import projectrts.model.core.entities.Resource;
import projectrts.model.core.entities.Worker;
import projectrts.model.core.utils.ModelUtils;
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
		EntityManager.getInstance().addNewPCE("Headquarter", player,new Position(-10f,-10f));
		EntityManager.getInstance().addNewNPCE("Resource", new Position(0f, 0f));
		Worker worker = (Worker) ModelUtils.INSTANCE.getPCEAtPosition(new Position(1f, 1f));
		Resource res = (Resource)ModelUtils.INSTANCE.getNonPlayerControlledEntity(new Position(0f,0f));
		
		ab.useAbility(worker, res.getPosition());
		int counter = 0;
		while(player.getResource()!=P.INSTANCE.getWorkerCarryAmount()*2+P.INSTANCE.getResourceStarterAmount()){
			ab.update(1);
			counter++;
			assertTrue(counter < 1000);	
		}
		
		
		
	}

}
