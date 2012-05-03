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
 * Test for GatherResourceAbility
 * @author Jakob Svensson
 *
 */
public class GatherResourceAbilityTest {

	@Test
	public void test() {
		new GameModel();
		
		Player player = new Player();
		EntityManager.getInstance().addNewPCE("Worker", player,new Position(2.5f,15.5f));
		EntityManager.getInstance().addNewPCE("Headquarter", player,new Position(40.5f,15.5));
		EntityManager.getInstance().addNewPCE("Barracks", player,new Position(40.5f,15.5));
		EntityManager.getInstance().addNewNPCE("Resource", new Position(15.5f, 15.5f));
		EntityManager.getInstance().update(1);
		Worker worker = (Worker) EntityManager.getInstance().getPCEAtPosition(new Position(2.5f, 15.5f));
		Resource res = (Resource) EntityManager.getInstance().getNonPlayerControlledEntity(new Position(15.5f,15.5f));
		MoveAbility move = new MoveAbility();
		move.initialize(worker);
		GatherResourceAbility ab = (GatherResourceAbility) AbilityFactory.INSTANCE.createMAbility(GatherResourceAbility.class.getSimpleName(),worker, move);
		ab.useAbility(res.getPosition());
		int counter = 0;

		while(player.getResources()!=12*2+Player.RESOURCE_START_AMOUNT){ //TODO Jakob: As a consequence of removing P, 12 is now hardcoded. should be fixed in some way
			ab.update(.5f);
			move.update(.5f);
			if(counter==12){
				EntityManager.getInstance().addNewPCE("Headquarter", player,new Position(8.5f,15.5));
				EntityManager.getInstance().update(1);
			}
			counter++;
			assertTrue(counter < 1000);	
		}
		
		
		
	}

}
