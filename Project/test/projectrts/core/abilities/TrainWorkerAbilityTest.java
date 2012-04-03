package projectrts.core.abilities;

import static org.junit.Assert.*;

import org.junit.Test;

import projectrts.model.core.EntityManager;
import projectrts.model.core.GameModel;
import projectrts.model.core.Player;
import projectrts.model.core.Position;
import projectrts.model.core.abilities.TrainWorkerAbility;
import projectrts.model.core.entities.Headquarter;
import projectrts.model.core.utils.ModelUtils;
/**
 * A test class for testing TrainWorkerAbility
 * @author Jakob Svensson
 *
 */
public class TrainWorkerAbilityTest {

	@Test
	public void test() {
		new GameModel();
		Player player = new Player();
		TrainWorkerAbility ab = new TrainWorkerAbility();
		EntityManager.getInstance().addNewPCE("Headquarter", player,new Position(10f,10f));
		Headquarter hq = (Headquarter) ModelUtils.INSTANCE.getPlayerControlledEntityAtPosition(new Position(10f, 10f));
		Position spawnPos = new Position(10+hq.getSize(),10+hq.getSize());
		
		ab.useAbility(hq, new Position(1,1));
		int counter = 0;
		while(player.getResource()!=-50||ModelUtils.INSTANCE.getPlayerControlledEntityAtPosition(spawnPos)==null){
			ab.update(1);
			counter++;
			assertTrue(counter < 1000);	
		}
		assertTrue(ModelUtils.INSTANCE.getPlayerControlledEntityAtPosition(spawnPos).getName().equals("Worker"));
	}

}
