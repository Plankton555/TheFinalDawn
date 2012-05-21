package projectrts.model.entities;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import projectrts.model.GameModel;
import projectrts.model.world.Position;

public class EntityManagerTest {

	@Test
	public void testGetEntitiesOfPlayer() {
		new GameModel();
		Player player = new Player(null);
		EntityManager.INSTANCE.resetData();
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(10, 10));
		EntityManager.INSTANCE.update(1);
		List<IPlayerControlledEntity> entities = EntityManager.INSTANCE
				.getEntitiesOfPlayer(player);

		assertTrue(entities.size() == 1);
		for (IPlayerControlledEntity e : entities) {
			assertTrue(e.getOwner().equals(player));
		}

		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(11, 10));
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(12, 10));
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(13, 10));
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(),
				new Player(null), new Position(10, 13));
		EntityManager.INSTANCE.update(1);
		entities = EntityManager.INSTANCE.getEntitiesOfPlayer(player);

		assertTrue(entities.size() == 4);
		for (IPlayerControlledEntity e : entities) {
			assertTrue(e.getOwner().equals(player));
		}
	}

	@Test
	public void testGetAllEntities() {
		new GameModel();
		Player player = new Player(null);
		EntityManager.INSTANCE.resetData();
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(10, 10));
		EntityManager.INSTANCE.update(1);
		List<IEntity> entities = EntityManager.INSTANCE.getAllEntities();

		assertTrue(entities.size() == 1);

		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(11, 10));
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(12, 10));
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(13, 10));
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(),
				new Player(null), new Position(10, 13));
		EntityManager.INSTANCE.update(1);
		entities = EntityManager.INSTANCE.getAllEntities();

		assertTrue(entities.size() == 5);
	}

	@Test
	public void testRequestNewEntityID() {
		List<Integer> numbers = new ArrayList<Integer>();
		int testAmount = 1000;

		for (int i = 0; i < testAmount; i++) {
			int nr = EntityManager.INSTANCE.requestNewEntityID();
			assertTrue(!numbers.contains(nr));
			numbers.add(nr);
		}
	}
	
	@Test
	public void testRemoveEntity() {
		new GameModel();
		Player player = new Player(null);
		EntityManager.INSTANCE.resetData();
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(10, 10));
		EntityManager.INSTANCE.addNewNPCE(Resource.class.getSimpleName(), new Position(20, 20));
		EntityManager.INSTANCE.update(1);
		
		IEntity entity = EntityManager.INSTANCE.getEntityAtPosition(new Position(10, 10));
		AbstractPlayerControlledEntity pce = null;
		AbstractNonPlayerControlledEntity npce = null;
		if (entity instanceof AbstractPlayerControlledEntity) {
			pce = (AbstractPlayerControlledEntity) entity;
		}
		entity = EntityManager.INSTANCE.getEntityAtPosition(new Position(20, 20));
		if (entity instanceof AbstractNonPlayerControlledEntity) {
			npce = (AbstractNonPlayerControlledEntity) entity;
		}
		assertTrue(pce != null);
		assertTrue(npce != null);
		assertTrue(EntityManager.INSTANCE.getAllEntities().size() == 2);
		
		EntityManager.INSTANCE.removeEntity(pce);
		EntityManager.INSTANCE.update(1);
		assertTrue(EntityManager.INSTANCE.getAllEntities().size() == 1);
		assertTrue(EntityManager.INSTANCE.getEntityAtPosition(new Position(10, 10)) == null);
		
		EntityManager.INSTANCE.removeEntity(npce);
		EntityManager.INSTANCE.update(1);
		assertTrue(EntityManager.INSTANCE.getAllEntities().size() == 0);
		assertTrue(EntityManager.INSTANCE.getEntityAtPosition(new Position(20, 20)) == null);
	}
	
	@Test
	public void testGetEntityAtPosition() {
		new GameModel();
		Player player = new Player(null);
		EntityManager.INSTANCE.resetData();
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(10, 10));
		EntityManager.INSTANCE.addNewNPCE(Resource.class.getSimpleName(), new Position(20, 20));
		EntityManager.INSTANCE.update(1);
		
		IEntity entity = EntityManager.INSTANCE.getEntityAtPosition(new Position(7, 7));
		assertTrue(entity == null);
		entity = EntityManager.INSTANCE.getEntityAtPosition(new Position(10, 10));
		assertTrue(entity instanceof AbstractPlayerControlledEntity);
		entity = EntityManager.INSTANCE.getEntityAtPosition(new Position(20, 20));
		assertTrue(entity instanceof AbstractNonPlayerControlledEntity);
	}
	
	@Test
	public void testGetPCEAtPositionPosition() {
		new GameModel();
		Player player = new Player(null);
		EntityManager.INSTANCE.resetData();
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(10, 10));
		EntityManager.INSTANCE.update(1);
		
		AbstractPlayerControlledEntity pce =
				EntityManager.INSTANCE.getPCEAtPosition(new Position(7, 7));
		assertTrue(pce == null);
		pce = EntityManager.INSTANCE.getPCEAtPosition(new Position(10, 10));
		assertTrue(pce != null);
	}
	
	@Test
	public void testGetPCEAtPositionPositionPlayer() {
		new GameModel();
		Player player1 = new Player(null);
		Player player2 = new Player(null);
		EntityManager.INSTANCE.resetData();
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player1,
				new Position(10, 10));
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player2,
				new Position(20, 20));
		EntityManager.INSTANCE.update(1);
		
		AbstractPlayerControlledEntity pce =
				EntityManager.INSTANCE.getPCEAtPosition(new Position(7, 7), player1);
		assertTrue(pce == null);
		pce = EntityManager.INSTANCE.getPCEAtPosition(new Position(10, 10), player1);
		assertTrue(pce != null);
		
		pce = EntityManager.INSTANCE.getPCEAtPosition(new Position(10, 10), player2);
		assertTrue(pce == null);
		pce = EntityManager.INSTANCE.getPCEAtPosition(new Position(20, 20), player2);
		assertTrue(pce != null);
		pce = EntityManager.INSTANCE.getPCEAtPosition(new Position(20, 20), player1);
		assertTrue(pce == null);
	}
	
	@Test
	public void testGetNPCEAtPosition() {
		new GameModel();
		EntityManager.INSTANCE.resetData();
		EntityManager.INSTANCE.addNewNPCE(Resource.class.getSimpleName(), new Position(10, 10));
		EntityManager.INSTANCE.update(1);
		
		AbstractNonPlayerControlledEntity npce =
				EntityManager.INSTANCE.getNPCEAtPosition(new Position(7, 7));
		assertTrue(npce == null);
		npce = EntityManager.INSTANCE.getNPCEAtPosition(new Position(10, 10));
		assertTrue(npce != null);
	}
	
	@Test
	public void testSelect() {
		new GameModel();
		Player player = new Player(null);
		EntityManager.INSTANCE.resetData();
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(10, 10));
		Position onUnit = new Position(10, 10);
		Position closeToUnit = new Position(9.5f, 9.5f);
		Position farFromUnit = new Position(5, 5);
		EntityManager.INSTANCE.update(1);
		AbstractPlayerControlledEntity pce = EntityManager.INSTANCE.getPCEAtPosition(onUnit);
		
		assertTrue(pce != null);

		assertTrue(EntityManager.INSTANCE.getSelectedEntities().size() == 0);
		assertTrue(!EntityManager.INSTANCE.isSelected(pce));
		assertTrue(EntityManager.INSTANCE.getSelectedEntitiesOfPlayer(player).size()==0);

		EntityManager.INSTANCE.select(onUnit, player);
		assertTrue(EntityManager.INSTANCE.getSelectedEntities().size() != 0);
		assertTrue(EntityManager.INSTANCE.isSelected(pce));
		assertTrue(EntityManager.INSTANCE.getSelectedEntitiesOfPlayer(player).size()==1);
		assertTrue(EntityManager.INSTANCE.getSelectedEntitiesOfPlayer(player).get(0).equals(pce));

		EntityManager.INSTANCE.select(closeToUnit, player);
		assertTrue(EntityManager.INSTANCE.getSelectedEntities().size() != 0);

		EntityManager.INSTANCE.select(farFromUnit, player);
		assertTrue(EntityManager.INSTANCE.getSelectedEntities().size() == 0);
	}

	@Test
	public void testGetNearbyEntities() {
		new GameModel();
		Player player = new Player(null);
		EntityManager.INSTANCE.resetData();
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(10, 10));
		EntityManager.INSTANCE.update(1);
		List<AbstractEntity> entities = EntityManager.INSTANCE
				.getNearbyEntities(new Position(15, 10), 2);
		assertTrue(entities.isEmpty());

		entities = EntityManager.INSTANCE.getNearbyEntities(
				new Position(15, 10), 6);
		assertTrue(entities.size() == 1);

		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(11, 10));
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(12, 10));
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(), player,
				new Position(13, 10));
		EntityManager.INSTANCE.addNewPCE(Worker.class.getSimpleName(),
				new Player(null), new Position(14, 10));
		EntityManager.INSTANCE.update(1);
		entities = EntityManager.INSTANCE.getNearbyEntities(
				new Position(15, 10), 3);
		assertTrue(entities.size() == 3);
	}
	
	@Test
	public void testGetClosestEnemy() {
		// TODO Implement this test
	}
	
	@Test
	public void testGetClosestEnemyStructure() {
		// TODO Implement this test
	}
}