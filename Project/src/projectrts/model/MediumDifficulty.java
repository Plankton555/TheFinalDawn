package projectrts.model;

/**
 * The medium difficulty
 * 
 * @author Heqir
 */
public final class MediumDifficulty extends AbstractDifficulty {

	public MediumDifficulty() {
		// TODO Markus: PMD: It is a good practice to call super() in a constructor
		waveInterval = 25;
		timeBeforeWaveZero = 60;
	}

	@Override
	public void update(float totalTimePassed, int nextWaveNumber) {
		if (nextWaveNumber % 2 == 0) {
			warriorsPerWave++;
		}

		if (nextWaveNumber % 2 == 0) {
			rangedPerWave++;
		}
	}

	public String toString() {
		return "Medium";
	}

}
