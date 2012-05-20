package projectrts.model;

/**
 * The hard difficulty.
 * 
 * @author Markus Ekström
 */
public final class HardDifficulty extends AbstractDifficulty {

	public HardDifficulty() {
		// TODO Markus: PMD: It is a good practice to call super() in a constructor
		waveInterval = 20;
		timeBeforeWaveZero = 60;
	}

	@Override
	public void update(float totalTimePassed, int nextWaveNumber) {
		if (nextWaveNumber % 2 == 0 || nextWaveNumber % 5 == 0) {
			warriorsPerWave++;
		}

		if (nextWaveNumber % 2 == 0) {
			rangedPerWave++;
		}
	}

	public String toString() {
		return "Hard";
	}
}
