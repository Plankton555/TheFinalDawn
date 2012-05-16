package projectrts.model;

import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Player;
import projectrts.model.entities.Warrior;
import projectrts.model.world.Position;

/**
 * A class representing a level in the game. 
 * Is in charge of sending waves of enemies towards the player.
 * @author Markus Ekström
 *
 */
public class Level {
	private int wave = 1;
	private float timePassed = 0;
	private int waveInterval = 20; // in seconds
	private float enemiesPerWave = 1;
	private float epwCoefficient = 1.5f;
	private final Player aiPlayer;
	
	/**
	 * A constructor for the level.
	 * @param aiPlayer The Player that the incoming waves is supposed to fight under.
	 */
	public Level(Player aiPlayer) {
		this.aiPlayer = aiPlayer;
	}
	
	/**
	 * Updates the level.
	 * @param tpf The time passed since last frame.
	 */
	public void update(float tpf) {
		timePassed += tpf;
		if(timePassed >= wave*waveInterval) {
			callNewWave();
			wave++;
		}
	}
	
	/**
	 * Sets the difficulty of the level.
	 * @param difficulty The desired difficulty.
	 */
	public void setDifficulty(Difficulty difficulty) {
		if(difficulty == Difficulty.EASY) {
			waveInterval = 30;
			epwCoefficient = 1.25f;
		} else if(difficulty == Difficulty.MEDIUM) {
			waveInterval = 20;
			epwCoefficient = 1.5f;
		} else if(difficulty == Difficulty.HARD){
			waveInterval = 20;
			epwCoefficient = 1.75f;
		} else {
			waveInterval = 15;
			epwCoefficient = 2;
			enemiesPerWave = 2;
		}
	}
	
	private void callNewWave() {
		int direction = wave % 4;
		
		switch(direction) {
		// TODO Markus: PMD: These statements may have some unnecessary parentheses
		// I think it means you can write case 1 instead of case(1)... I dunno :S //Plankton
			case(0): 
				spawnEnemies(new Position(0, 50), Math.round(enemiesPerWave));
				break;
			case(1): 
				spawnEnemies(new Position(50, 0), Math.round(enemiesPerWave));
				break;
			case(2): 
				spawnEnemies(new Position(100, 50), Math.round(enemiesPerWave));
				break;
			default: 
				spawnEnemies(new Position(50, 100), Math.round(enemiesPerWave));
		}
		
		if(enemiesPerWave < 4) {
			enemiesPerWave *= epwCoefficient;
		}
		if(waveInterval > 5) {
			waveInterval--;
		}
		
	}
	
	
	private void spawnEnemies(Position pos, int amount) {
		for(int i = 0; i < amount; i++) {
			EntityManager.INSTANCE.addNewPCE(Warrior.class.getSimpleName(), aiPlayer, pos);
			pos = new Position(pos.getX(), pos.getY() + 1);
		}
	}
}
