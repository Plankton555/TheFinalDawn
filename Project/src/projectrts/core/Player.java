package projectrts.core;

import java.util.ArrayList;
import java.util.List;

public class Player implements IPlayer {
	List<Unit> units = new ArrayList<Unit>();
	List<Unit> selectedUnits = new ArrayList<Unit>();
	
	@Override
	public void select(Position pos) {
		for(Unit unit: units){
			
		}
		
	}

	@Override
	public void moveSelectedTo(Position p) {
		// TODO Player.moveSelectedTo()
		
	}
	
	@Override
	public List<IUnit> getUnits() {
		// TODO Player.getUnits()
		return null;
	}
	
	/**
	 * Updates the player.
	 * @param tpf Time per frame
	 */
	public void update(float tpf)
	{
		// TODO player.update()
	}

}
