package projectrts.model.entities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectrts.model.GameModel;
import projectrts.model.world.Position;

public class EntityManagerTest {

	
	// This test requires the player to instantiate a unit in the constructor
	@Test
	public void testSelect() {
		new GameModel();
		Player player = new Player();
		EntityManager.getInstance().addNewPCE("Worker", player,new Position(10,10));
		Position onUnit = new Position(10, 10);
		Position closeToUnit = new Position(9.5f, 9.5f);
		Position farFromUnit = new Position(5, 5);
		EntityManager.getInstance().update(1);
		
		 
		assertTrue(EntityManager.getInstance().getSelectedEntities().size() == 0);
		
		EntityManager.getInstance().select(onUnit, player);
		assertTrue(EntityManager.getInstance().getSelectedEntities().size() != 0);
		
		
		EntityManager.getInstance().select(closeToUnit, player);
		assertTrue(EntityManager.getInstance().getSelectedEntities().size() != 0);
		
		
		EntityManager.getInstance().select(farFromUnit, player);
		assertTrue(EntityManager.getInstance().getSelectedEntities().size() == 0);
		
	}
	
	// TODO Plankton: !!Test the methods that are written by Plankton

	
	
}
