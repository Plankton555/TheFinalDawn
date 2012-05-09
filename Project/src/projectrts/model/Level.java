package projectrts.model;

import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Player;
import projectrts.model.entities.Warrior;
import projectrts.model.world.Position;

//TODO Markus: Add javadoc!
public class Level {
	private int wave = 1;
	private float timePassed = 0;
	private int waveInterval = 20; // in seconds
	private float monstersPerWave = 1;
	private float mpwCoefficient = 1.5f;
	private final Player aiPlayer;
	
	public enum Difficulty{EASY, MEDIUM, HARD, NIGHTMARE}
	
	private Difficulty currentDifficulty = Difficulty.MEDIUM;
	
	public Level(Player aiPlayer) {
		this.aiPlayer = aiPlayer;
	}
	
	public void update(float tpf) {
		timePassed += tpf;
		if(timePassed >= wave*waveInterval) {
			callNewWave();
			wave++;
		}
	}
	
	public void setDifficulty(Difficulty difficulty) {
		currentDifficulty = difficulty;
		if(currentDifficulty == Difficulty.EASY) {
			waveInterval = 30;
			mpwCoefficient = 1.25f;
		} else if(currentDifficulty == Difficulty.MEDIUM) {
			waveInterval = 20;
			mpwCoefficient = 1.5f;
		} else if(currentDifficulty == Difficulty.HARD){
			waveInterval = 20;
			mpwCoefficient = 1.75f;
		} else {
			waveInterval = 15;
			mpwCoefficient = 2;
			monstersPerWave = 2;
		}
	}
	
	public Difficulty getCurrentDifficulty() {
		return currentDifficulty;
	}
	
	private void callNewWave() {
		int direction = wave % 4;
		
		switch(direction) {
			case(0): 
				spawnEnemies(new Position(0, 50), Math.round(monstersPerWave));
				break;
			case(1): 
				spawnEnemies(new Position(50, 0), Math.round(monstersPerWave));
				break;
			case(2): 
				spawnEnemies(new Position(100, 50), Math.round(monstersPerWave));
				break;
			default: 
				spawnEnemies(new Position(50, 100), Math.round(monstersPerWave));
		}
		monstersPerWave *= mpwCoefficient;
		
	}
	
	
	private void spawnEnemies(Position pos, int amount) {
		for(int i = 0; i < amount; i++) {
			EntityManager.getInstance().addNewPCE(Warrior.class.getSimpleName(), aiPlayer, pos);
		}
	}
}
