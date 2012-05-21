package projectrts.model;

/**
 * The nightmare difficulty.
 * 
 * @author Markus Ekström
 */
public final class NightmareDifficulty extends AbstractDifficulty {

	public NightmareDifficulty() {
		super();
		waveInterval = 15;
		timeBeforeWaveZero = 60;
	}

	@Override
	public void update(float totalTimePassed, int nextWaveNumber) {
		if (nextWaveNumber % 5 == 0) {
			warriorsPerWave++;
		}

		if (nextWaveNumber % 5 == 0) {
			rangedPerWave++;
		}
	}

	public String toString() {
		return "Nightmare";
	}
}
