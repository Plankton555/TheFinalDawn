package projectrts.model.player;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void test() {
		Player p = new Player();
		assertTrue(p.getResources()==Player.RESOURCE_START_AMOUNT);
		
		p.modifyResource(10);
		assertTrue(p.getResources()==Player.RESOURCE_START_AMOUNT+10);
		
		p.modifyResource(-30);
		assertTrue(p.getResources()==Player.RESOURCE_START_AMOUNT-20);
	}

}
