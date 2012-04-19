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
	

	
	// TODO Jakob: ADD JAVADOC
	public int getResource(){
		return resources; 
	}
	
	public void modifyResource(int amount){
		resources+=amount;
	}
	
}
