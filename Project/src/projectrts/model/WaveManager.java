package projectrts.model;

import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Player;
import projectrts.model.entities.Ranged;
import projectrts.model.entities.Warrior;
import projectrts.model.world.Position;

/**
 * A class representing a level in the game. Is in charge of sending waves of
 * enemies towards the player.
 * 
 * @author Markus Ekström
 * 
 */
class WaveManager {
	private int wave = 0;
	private float timePassed = 0;
	private final Player aiPlayer;
	private AbstractDifficulty difficulty;

	/**
	 * A constructor for the level.
	 * 
	 * @param aiPlayer
	 *            The Player that the incoming waves is supposed to fight under.
	 */
	public WaveManager(Player aiPlayer) {
		this.aiPlayer = aiPlayer;
		this.difficulty = new MediumDifficulty();
	}

	/**
	 * Updates the level.
	 * 
	 * @param tpf
	 *            The time passed since last frame.
	 */
	public void update(float tpf) {
		timePassed += tpf;
		
		if((timePassed > difficulty.getTimeBeforeWaveZero() && wave == 0) || (wave > 0 && 
				timePassed >= (wave * difficulty.getWaveInterval() + difficulty.getTimeBeforeWaveZero()))) {
			callNewWave();
			wave++;
			difficulty.update(timePassed, wave);
		}
	}

	/**
	 * Sets the difficulty of the level.
	 * 
	 * @param difficulty
	 *            The desired difficulty.
	 */
	public void setDifficulty(AbstractDifficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public AbstractDifficulty getDifficulty() {
		return difficulty;
	}

	private void callNewWave() {
		int direction = wave % 4;

		switch (direction) {
		case 0:
			spawnEnemies(new Position(1, 50));
			break;
		case 1:
			spawnEnemies(new Position(50, 1));
			break;
		case 2:
			spawnEnemies(new Position(99, 50));
			break;
		default:
			spawnEnemies(new Position(50, 99));
		}
	}

	private void spawnEnemies(Position pos) {
		for (int i = 0; i < difficulty.getNumberOfWarriorsPerWave(); i++) {
			EntityManager.INSTANCE.addNewPCE(Warrior.class.getSimpleName(),
					aiPlayer, pos);
		}
		
		for (int i = 0; i < difficulty.getNumberOfRangedPerWave(); i++) {
			EntityManager.INSTANCE.addNewPCE(Ranged.class.getSimpleName(),
					aiPlayer, pos);
		}
	}
}