package projectrts.model.utils;


/**
 * Utility class
 * @author Filip Brynfors. Modified by Jakob Svensson & Bjorn Persson Mattsson
 *
 */
public class ModelUtils {
	
	/**
	 * Determines whether a number is in a certain interval or not.
	 * @param value The value to be examined.
	 * @param low Lower limit.
	 * @param high Higher limit.
	 * @return true if the number is in the interval, otherwise false.
	 */
	public static boolean isWithin(double value, double low, double high){
		return (value>=low && value<=high);
		// TODO Anyone: Check if able to use this from Guava
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
