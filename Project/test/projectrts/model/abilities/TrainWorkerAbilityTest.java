package projectrts.model.abilities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectrts.model.GameModel;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Headquarter;
import projectrts.model.entities.Player;
import projectrts.model.world.Position;
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
		EntityManager.INSTANCE.addNewPCE("Headquarter", player,new Position(10f,10f));
		EntityManager.INSTANCE.update(1);
		Headquarter hq = (Headquarter)  EntityManager.INSTANCE.getPCEAtPosition(new Position(10f, 10f));
		TrainWorkerAbility ab = (TrainWorkerAbility)AbilityFactory.INSTANCE.createAbility(TrainWorkerAbility.class.getSimpleName(),hq);
	
		ab.useAbility(new Position(1,1));
		int counter = 0;
		while(!ab.isFinished()){
			ab.update(1);
			counter++;
			assertTrue(counter < 1000);	
		}
	}

}
