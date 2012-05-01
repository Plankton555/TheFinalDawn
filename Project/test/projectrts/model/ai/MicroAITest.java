package projectrts.model.ai;

import static org.junit.Assert.*;

import javax.vecmath.Vector2d;

import org.junit.Before;
import org.junit.Test;

import projectrts.model.GameModel;
import projectrts.model.entities.EntityFactory;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.abilities.AttackAbility;
import projectrts.model.entities.units.Warrior;
import projectrts.model.player.Player;
import projectrts.model.utils.Position;


public class MicroAITest {
	private Player aiPlayer;
	private Player humanPlayer;
	private MicroAI myMicroAI;
	private MicroAI enemyMicroAI;
	private GameModel model;
	private PlayerControlledEntity myWarrior;
	private PlayerControlledEntity enemyWarrior;
	private Position startPos;
	
	
	
	@Before
	public void setUp() {
		model = new GameModel();
		aiPlayer = new Player();
		humanPlayer = new Player();
		startPos = new Position(32.5, -32.5);
		EntityManager.getInstance().addNewPCE(Warrior.class.getSimpleName(), humanPlayer, new Position(32.5, -42.5));
		EntityManager.getInstance().addNewPCE(Warrior.class.getSimpleName(), aiPlayer, new Position(32.5, -32.5));
		model.update(1f);
		myWarrior = EntityManager.getInstance().getPCEAtPosition(new Position(32.5, -42.5), humanPlayer);
		enemyWarrior = EntityManager.getInstance().getPCEAtPosition(new Position(32.5, -32.5), aiPlayer);
	}
	
	@Test
	public void testUpdate() {
		//Set the warriors next to each other.
		myWarrior.setPosition(new Position(32.5, -34.5));
		enemyWarrior.setPosition(new Position(32.5, -33.5));
		
		//Select the warrior controlled by the human player and make sure it is selected.
		EntityManager.getInstance().select(new Position(32.5, -34.5), humanPlayer);
		assertTrue(EntityManager.getInstance().isSelected(myWarrior));

		//Update the model and check that only the human controlled warrior has recieved any damage.
		model.update(1);
		assertFalse(myWarrior.isDead());
		assertFalse(enemyWarrior.isDead());
		assertFalse(enemyWarrior.getCurrentHealth() < enemyWarrior.getMaxHealth());
		assertTrue(myWarrior.getCurrentHealth() < myWarrior.getMaxHealth());
		
		//Set the position of the human controlled warrior to just outside the enemyWarriors sight range.
		myWarrior.setPosition(new Position(enemyWarrior.getPosition().getX(), enemyWarrior.getPosition().getY() + myWarrior.getSightRange() + 1));
		
		//Check that the enemy warrior stays put.
		Position enemyPosition = enemyWarrior.getPosition(); 
		model.update(1);
		assertTrue(enemyWarrior.getPosition().equals(enemyPosition));
	}
}
