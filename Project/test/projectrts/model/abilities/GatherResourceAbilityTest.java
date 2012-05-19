package projectrts.model.abilities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectrts.model.GameModel;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Player;
import projectrts.model.entities.Resource;
import projectrts.model.entities.Worker;
import projectrts.model.world.Position;
/**
 * Test for GatherResourceAbility
 * @author Jakob Svensson
 *
 */
public class GatherResourceAbilityTest {

	@Test
	public void test() {
		new GameModel();
		
		Player player = new Player();
		EntityManager.INSTANCE.addNewPCE("Worker", player,new Position(2.5f,15.5f));
		EntityManager.INSTANCE.addNewPCE("Headquarter", player,new Position(10.5f,15.5));
		EntityManager.INSTANCE.addNewPCE("Barracks", player,new Position(35.5f,15.5));
		EntityManager.INSTANCE.addNewNPCE("Resource", new Position(15.5f, 15.5f));
		EntityManager.INSTANCE.update(1);
		Worker worker = (Worker) EntityManager.INSTANCE.getPCEAtPosition(new Position(2.5f, 15.5f));
		Resource res = (Resource) EntityManager.INSTANCE.getNPCEAtPosition(new Position(15.5f,15.5f));
		MoveAbility move = new MoveAbility();
		move.initialize(worker);
		GatherResourceAbility ab = (GatherResourceAbility) AbilityFactory.createUsingMoveAbility(GatherResourceAbility.class.getSimpleName(),worker, move);
		ab.useAbility(res.getPosition());
		int counter = 0;

		while(player.getResources()!=GatherResourceAbility.RESOURCE_CARRIED_AMOUNT*2+Player.RESOURCE_START_AMOUNT){ 
			ab.update(.5f);
			move.update(.5f);
			if(counter==12){
				EntityManager.INSTANCE.addNewPCE("Headquarter", player,new Position(8.5f,15.5));
				EntityManager.INSTANCE.update(1);
			}
			counter++;
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			assertTrue(counter < 100);	
		}
		
		
		
	}

}
