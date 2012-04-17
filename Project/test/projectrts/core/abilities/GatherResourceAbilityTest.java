package projectrts.core.abilities;

import static org.junit.Assert.*;

import org.junit.Test;

import projectrts.model.core.EntityManager;
import projectrts.model.core.GameModel;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
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
		GatherResourceAbility ab = new GatherResourceAbility();
		Player player = new Player();
		EntityManager.getInstance().addNewPCE("Worker", player,new Position(1f,1f));
		EntityManager.getInstance().addNewPCE("Headquarter", player,new Position(10f,10f));
		EntityManager.getInstance().addNewPCE("Headquarter", player,new Position(-10f,-10f));
		EntityManager.getInstance().addNewNPCE("Resource", new Position(0f, 0f));
		Worker worker = (Worker) ModelUtils.INSTANCE.getPlayerControlledEntityAtPosition(new Position(1f, 1f));
		Resource res = (Resource)ModelUtils.INSTANCE.getNonPlayerControlledEntity(new Position(0f,0f));
		
		ab.useAbility(worker, res.getPosition());
		int counter = 0;
		int resource = player.getResource();
		while(player.getResource()!=resource+res.mine()*2){
			ab.update(1);
			counter++;
			assertTrue(counter < 1000);	
		}
		
		
		
	}

}
