package projectrts.model.abilities;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import projectrts.model.GameModel;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Player;
import projectrts.model.entities.Warrior;
import projectrts.model.world.Position;

public class AbilityManagerTest {
	Warrior warrior;
	AbilityManager am;
	@Before
	public void setUp() throws Exception {
		new GameModel();
		am = new AbilityManager();
		EntityManager.INSTANCE.addNewPCE(Warrior.class.getSimpleName(), new Player(null), new Position(5,5));
		EntityManager.INSTANCE.update(1);
		warrior  = (Warrior) EntityManager.INSTANCE.getPCEAtPosition(new Position(5,5));
		
	}
	
	@Test
	public void testAddAbilitiesToEntity(){		
		List<IAbility> abl = am.getAbilities(warrior);
		assertTrue("Attack".equals(abl.get(0).getName()));
		assertTrue("Move".equals(abl.get(1).getName()));		
	}
	
	@Test
	public void testDoAbility(){
		am.doAbility(MoveAbility.class.getSimpleName(), new Position (3.5,4.5), warrior);
		int counter = 0;
		while(!warrior.getPosition().equals(new Position(3.5,4.5))){
			am.update(1);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			counter ++;
			System.out.println(warrior.getPosition());
			assertTrue(counter<100);
		}
	}
	
	
}