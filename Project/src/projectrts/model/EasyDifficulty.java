package projectrts.model;

/**
 * The easy difficulty.
 * 
 * @author Markus Ekstrom
 */
public final class EasyDifficulty extends AbstractDifficulty {

	public EasyDifficulty() {
		super();
		waveInterval = 30;
		timeBeforeWaveZero = 60;
	}

	@Override
	public void update(float totalTimePassed, int nextWaveNumber) {
		if (nextWaveNumber % 8 == 0) {
			warriorsPerWave++;
		}

		if (nextWaveNumber % 8 == 0) {
			rangedPerWave++;
		}
	}

	public String toString() {
		return "Easy";
	}
}
