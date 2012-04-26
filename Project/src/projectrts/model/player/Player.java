package projectrts.model.player;

import projectrts.model.constants.P;

/**
 * Player class for handling all of one players units.
 * @author Björn Persson Mattson, Modified by Filip Brynfors, Jakob Svensson
 */
public class Player implements IPlayer {
	public static final int RESOURCE_START_AMOUNT = 2000;
	private int resources;
	
	/**
	 * Constructs a player
	 */
	public Player(){
		resources=RESOURCE_START_AMOUNT;
	}
	

	
	@Override
	public int getResources(){
		return resources; 
	}
	
	/**
	 * Changes the amount of resources the player has
	 * @param amount the amount to change with
	 */
	public void modifyResource(int amount){
		resources+=amount;
	}
	
}
