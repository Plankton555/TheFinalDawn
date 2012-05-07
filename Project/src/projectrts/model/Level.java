package projectrts.model;

import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Player;
import projectrts.model.entities.Warrior;
import projectrts.model.world.Position;

public class Level {
	private int wave = 1;
	private float timePassed = 0;
	private static final int WAVE_INTERVAL = 20; // in seconds
	private final Player aiPlayer;
	
	public Level(Player aiPlayer) {
		this.aiPlayer = aiPlayer;
	}
	
	
	public void update(float tpf) {
		timePassed += tpf;
		if(timePassed >= wave*WAVE_INTERVAL) {
			callNewWave();
			wave++;
		}
	}
	
	private void callNewWave() {
		int direction = wave % 4;
		
		switch(direction) {
			case(0): 
				spawnEnemies(new Position(0, 50), wave);
				break;
			case(1): 
				spawnEnemies(new Position(50, 0), wave);
				break;
			case(2): 
				spawnEnemies(new Position(100, 50), wave);
				break;
			default: 
				spawnEnemies(new Position(50, 100), wave);
		}
	}
	
	
	private void spawnEnemies(Position pos, int amount) {
		for(int i = 0; i < amount; i++) {
			EntityManager.getInstance().addNewPCE(Warrior.class.getSimpleName(), aiPlayer, pos);
		}
	}
}
