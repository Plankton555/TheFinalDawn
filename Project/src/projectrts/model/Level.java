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
	private float enemiesPerWave = 1;
	private float epwCoefficient = 1.5f;
	private final Player aiPlayer;
	
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
		enemiesPerWave *= epwCoefficient;
		
	}
	
	
	private void spawnEnemies(Position pos, int amount) {
		for(int i = 0; i < amount; i++) {
			EntityManager.getInstance().addNewPCE(Warrior.class.getSimpleName(), aiPlayer, pos);
		}
	}
}
