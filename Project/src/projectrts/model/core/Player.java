package projectrts.model.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Player class for handling all of one players units.
 * @author Björn Persson Mattson, Modified by Filip Brynfors
 */
public class Player implements IPlayer {
	private List<Unit> units = new ArrayList<Unit>();
	private List<Unit> selectedUnits = new ArrayList<Unit>();
	
	/**
	 * Constructs a player
	 */
	public Player(){
		//TODO: Temp test unit
		units.add(new Unit(new Position(10,10), this));
	}
	
	@Override
	public void select(Position pos) {
		//TODO: Add support for selection of multiple units and enemy units
		selectedUnits.clear();
		
		for(Unit unit: units){
			float size = unit.getSize();
			Position unitPos = unit.getPosition();
			if(unitPos.getX()>= pos.getX()-size/2 && unitPos.getX()<= pos.getX()+size/2){
				if(unitPos.getY()>= pos.getY()-size/2 && unitPos.getY()<= pos.getY()+size/2){
					
					
					selectedUnits.add(unit);
					break;
				}
			}
		}
	}

	@Override
	public void moveSelectedTo(Position p) {
		for(Unit unit: selectedUnits){
			unit.moveTo(p);
		}
	}
	
	@Override
	public List<IUnit> getUnits() {
		List<IUnit> iUnits = new ArrayList<IUnit>();
		for(Unit unit: units){
			iUnits.add(unit);
		}
		return iUnits;
	}

	@Override
	public List<IUnit> getSelectedUnits() {
		List<IUnit> iUnits = new ArrayList<IUnit>();
		for(Unit unit: selectedUnits){
			iUnits.add(unit);
		}
		return iUnits;
	}
	
	/**
	 * Updates the player.
	 * @param tpf Time per frame
	 */
	public void update(float tpf)
	{
		for(Unit unit: units){
			unit.update(tpf);
		}
	}
}
