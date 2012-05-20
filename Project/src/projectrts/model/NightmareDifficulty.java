package projectrts.model;

/**
 * The nightmare difficulty.
 * 
 * @author Markus Ekström
 */
public final class NightmareDifficulty extends AbstractDifficulty {

	public NightmareDifficulty() {
		// TODO Markus: PMD: It is a good practice to call super() in a constructor
		waveInterval = 15;
		timeBeforeWaveZero = 60;
	}

	@Override
	public void update(float totalTimePassed, int nextWaveNumber) {
		if (nextWaveNumber % 2 == 0 || nextWaveNumber % 3 == 0) {
			warriorsPerWave++;
		}

		if (nextWaveNumber % 2 == 0 || nextWaveNumber % 5 == 0) {
			rangedPerWave++;
		}
	}

	public String toString() {
		return "Nightmare";
	}
}
