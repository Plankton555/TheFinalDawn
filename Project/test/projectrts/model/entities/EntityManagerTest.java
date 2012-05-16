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
		EntityManager.INSTANCE.addNewPCE("Worker", player,new Position(10,10));
		Position onUnit = new Position(10, 10);
		Position closeToUnit = new Position(9.5f, 9.5f);
		Position farFromUnit = new Position(5, 5);
		EntityManager.INSTANCE.update(1);
		
		 
		assertTrue(EntityManager.INSTANCE.getSelectedEntities().size() == 0);
		
		EntityManager.INSTANCE.select(onUnit, player);
		assertTrue(EntityManager.INSTANCE.getSelectedEntities().size() != 0);
		
		
		EntityManager.INSTANCE.select(closeToUnit, player);
		assertTrue(EntityManager.INSTANCE.getSelectedEntities().size() != 0);
		
		
		EntityManager.INSTANCE.select(farFromUnit, player);
		assertTrue(EntityManager.INSTANCE.getSelectedEntities().size() == 0);
		
	}
	
	@Test
	public void testGetEntitiesOfPlayer()
	{
		// TODO Plankton Implement
	}
	
	@Test
	public void testGetAllEntities()
	{
		// TODO Plankton Implement
	}
	
	@Test
	public void testRequestNewEntityID()
	{
		// TODO Plankton Implement
	}
	
	@Test
	public void testGetNearbyEntities()
	{
		// TODO Plankton Implement
	}
}
