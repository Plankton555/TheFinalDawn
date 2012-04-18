package projectrts.core.abilities;

import static org.junit.Assert.*;

import org.junit.Test;

import projectrts.model.core.EntityFactory;
import projectrts.model.core.EntityManager;
import projectrts.model.core.GameModel;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.AbilityFactory;
import projectrts.model.core.abilities.MineResourceAbility;
import projectrts.model.core.entities.Resource;
import projectrts.model.core.entities.Worker;
import projectrts.model.core.utils.ModelUtils;
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
		Worker worker = (Worker) ModelUtils.INSTANCE.getPCEAtPosition(new Position(1f, 1f));
		Resource res = (Resource)ModelUtils.INSTANCE.getNonPlayerControlledEntity(new Position(5f,5f));
		
		ab.useAbility(worker, res.getPosition());
		int counter = 0;
		while(!ab.isFinished()){
			
			ab.update(1);
			counter++;
			assertTrue(counter < 1000);	
		}
	}

}
