package projectrts.model.pathfinding;

import static org.junit.Assert.assertTrue;

import javax.vecmath.Vector2d;

import org.junit.BeforeClass;
import org.junit.Test;

import projectrts.model.world.Position;

/**
 * 
 * @author Bjorn Persson Mattsson
 *
 */
public class PositionTest {

	private static double d1;
	private static double d2;
	private static double d3;
	private static Position p1;
	private static Position p2;
	private static Position p3;
	private static Position p4;
	
	@BeforeClass
	public static void beforeClass()
	{
		d1 = 5.3;
		d2 = 8.32;
		d3 = 9.312;
		p1 = new Position(1.5, 2);
		p2 = new Position(5, 3.98);
		p3 = new Position(1.5, 2);
		p4 = new Position(1.5, 3);
	}
	
	@Test
	public final void testHashCode() {
		assertTrue(p1.hashCode() == p1.hashCode());
		assertTrue(p1.hashCode() != p2.hashCode());
		assertTrue(p1.hashCode() == p3.hashCode());
		
		assertTrue(p2.hashCode() != p1.hashCode());
		assertTrue(p2.hashCode() == p2.hashCode());
		assertTrue(p2.hashCode() != p3.hashCode());
		
		assertTrue(p3.hashCode() == p1.hashCode());
		assertTrue(p3.hashCode() != p2.hashCode());
		assertTrue(p3.hashCode() == p3.hashCode());
	}

	@Test
	public final void testPositionDoubleDouble() {
		Position p = new Position(d1, d2);
		assertTrue(p!=null);
		assertTrue(p.getX() == d1);
		assertTrue(p.getY() == d2);
	}

	@Test
	public final void testPositionPosition() {
		Position p = new Position(p1);
		assertTrue(p.equals(p1));
		
		p = new Position(p2);
		assertTrue(p.equals(p2));
		assertTrue(!p.equals(p1));
	}

	@Test
	public final void testCopy() {
		Position p = p1.copy();
		assertTrue(p.equals(p1));
		
		p = p2.copy();
		assertTrue(p.equals(p2));
		assertTrue(!p.equals(p1));
	}

	@Test
	public final void testEqualsObject() {
		assertTrue(p1.equals(p1));
		assertTrue(!p1.equals(p2));
		assertTrue(p1.equals(p3));
		
		assertTrue(!p1.equals(null));
		assertTrue(!p1.equals(new Double(5)));
		assertTrue(!p1.equals(p4));
	}

	@Test
	public final void testGetX() {
		Position p = new Position(d1, d2);
		assertTrue(p.getX() == d1);
		assertTrue(p.getX() != d2);
	}

	@Test
	public final void testGetY() {
		Position p = new Position(d1, d2);
		assertTrue(p.getY() == d2);
		assertTrue(p.getY() != d1);
	}

	@Test
	public final void testToString() {
		String str = p1.toString();
		
		assertTrue(str.equals(p1.getX() + ";" + p1.getY()));
	}

	@Test
	public final void testAdd() {
		Position p = p1.add(d3, new Vector2d(0, 1));
		assertTrue(p.getX() == p1.getX());
		assertTrue(p.getY() == p1.getY()+d3);
		
		p = p1.add(d3, new Vector2d(0, 0));
		assertTrue(p.equals(p1));
	}

	@Test
	public final void testGetVectorBetween() {
		Vector2d delta1 = new Vector2d(p1.getX()-p2.getX(), p1.getY()-p2.getY());
		Vector2d delta2 = Position.getVectorBetween(p2, p1);
		
		assertTrue(delta1.equals(delta2));
	}

	@Test
	public final void testGetDistance() {
		Vector2d delta = Position.getVectorBetween(p1, p2);
		double distance = Position.getDistance(p1, p2);
		
		assertTrue(delta.length() == distance);
	}

}
