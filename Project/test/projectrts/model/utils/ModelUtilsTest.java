package projectrts.model.utils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Bjorn Persson Mattsson
 *
 */
public class ModelUtilsTest {

	private static double d1;
	private static double d2;
	private static double d3;
	
	@BeforeClass
	public static void beforeClass()
	{
		d1 = 1.42;
		d2 = 5.72;
		d3 = 9.13;
	}
	
	@Test
	public final void testIsWithin() {
		assertTrue(ModelUtils.isWithin(3.2, d1, d2));
		assertTrue(ModelUtils.isWithin(3.2, d1, d3));
		assertTrue(!ModelUtils.isWithin(3.2, d2, d3));
		assertTrue(!ModelUtils.isWithin(8.2, d1, d2));
	}

	@Test
	public final void testClamp() {
		assertTrue(ModelUtils.clamp(d1, d2, d3) == d2);
		assertTrue(ModelUtils.clamp(d2, d1, d3) == d2);
		assertTrue(ModelUtils.clamp(d1, d1, d3) == d1);
		assertTrue(ModelUtils.clamp(d1, d2, d3) == d2);
		assertTrue(ModelUtils.clamp(10, d1, d3) == d3);
	}

}
