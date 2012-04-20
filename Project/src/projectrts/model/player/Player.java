package projectrts.model.player;

import projectrts.model.constants.P;

/**
 * Player class for handling all of one players units.
 * @author Björn Persson Mattson, Modified by Filip Brynfors, Jakob Svensson
 */
public class Player implements IPlayer {

	private int resources;
	
	/**
	 * Constructs a player
	 */
	public Player(){
		resources=P.INSTANCE.getResourceStarterAmount();
	}
	

	
	/**
	 * Returns the players current amount of resources
	 * @return The amount of resources
	 */
	public int getResources(){
		return resources; 
	}
	
	public void modifyResource(int amount){
		resources+=amount;
	}
	
}
