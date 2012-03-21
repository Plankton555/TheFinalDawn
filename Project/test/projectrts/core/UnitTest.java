package projectrts.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTest {

	@Test
	public void testUnit() {
		Player player = new Player();
		Position pos = new Position(15, 13);
		Unit unit = new Unit(pos, player);
		
		assertTrue(pos.equals(unit.getPosition()));
		assertTrue(player.equals(unit.getOwner()));
	}

	/* Already tested in constructor
	@Test
	public void testGetPosition() {
		fail("Not yet implemented");
	}
	*/

	@Test
	public void testGetSize() {
		Player player = new Player();
		Position pos = new Position(15, 13);
		Unit unit = new Unit(pos, player);
		
		assertTrue(unit.getSize() == 1); // Does basically nothing atm
	}

	/* Already tested in constructor
	@Test
	public void testGetOwner() {
		fail("Not yet implemented");
	}
	*/

	@Test
	public void testMove() {
		Player player = new Player();
		Position pos = new Position(15, 13);
		Unit unit = new Unit(pos, player);
		Position target = new Position(200, 153);
		
		int counter = 0;
		unit.moveTo(target);
		while (!unit.getPosition().equals(target))
		{
			unit.update(1);
			counter++;
			
			assertTrue(counter < 1000);
		}
		System.out.println("counter: " + counter);
	}
}
