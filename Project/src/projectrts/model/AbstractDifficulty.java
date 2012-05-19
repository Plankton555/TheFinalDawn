package projectrts.model;

/**
 * A base class for difficulties. 
 * 
 * @author Markus Ekstrom
 */
public abstract class AbstractDifficulty implements IDifficulty{
	protected int waveInterval = 20;
	protected int warriorsPerWave = 1;
	protected int rangedPerWave = 0;
	protected int timeBeforeWaveZero = 60; 
	
	public float getWaveInterval() {
		return waveInterval;
	}
	
	public int getNumberOfWarriorsPerWave() {
		return warriorsPerWave;
	}
	
	public int getNumberOfRangedPerWave() {
		return rangedPerWave;
	}
	
	public int getTimeBeforeWaveZero() {
		return timeBeforeWaveZero;
	}
	
	/**
	 * Call this method after each wave for intended result. The wave before the first
	 * update should be wave zero.
	 * @param newWaveNumber The wave number of the next wave.
	 */
	public abstract void update(float totalTimePassed, int newWaveNumber);
}