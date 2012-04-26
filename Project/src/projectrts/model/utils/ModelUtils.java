package projectrts.model.utils;


/**
 * Utility class
 * @author Filip Brynfors. Modified by Jakob Svensson & Bjorn Persson Mattsson
 *
 */
public class ModelUtils {
	
	// TODO Anyone: Add javadoc
	public static boolean isWithin(double p, double low, double high){
		return (p>=low && p<=high);
	}
	
	/**
	 * Restricts a value to be within a specified range.
	 * @param value The value to clamp.
	 * @param min The minimum value. If value is less than min, min will be returned.
	 * @param max The maximum value. If value is greater than max, max will be returned.
	 * @return If value > max, max will be returned. If value < min, min will be returned.
	 * If min <= value >= max, value will be returned.
	 */
	public static double clamp(double value, double min, double max) {
		return (value > max ? max : (value < min ? min : value));
	}
}
