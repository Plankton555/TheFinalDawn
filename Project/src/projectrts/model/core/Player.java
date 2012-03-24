package projectrts.model.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import projectrts.model.core.entities.AbstractEntity;
import projectrts.model.core.entities.IEntity;
import projectrts.model.core.entities.IPlayerControlledEntity;
import projectrts.model.core.entities.Unit;

/**
 * Player class for handling all of one players units.
 * @author Björn Persson Mattson, Modified by Filip Brynfors
 */
public class Player implements IPlayer {

	// TODO Change this list to a set?
	private List<IPlayerControlledEntity> selectedEntities = new ArrayList<IPlayerControlledEntity>();
	
	/**
	 * Constructs a player
	 */
	public Player(){
		//TODO: Temp test unit
		//units.add(new Unit(new Position(10,10), this));
	}
	
	@Override
	public void select(Position pos) {
		//TODO: Add support for selection of multiple units and enemy units
		selectedEntities.clear();
		
		List<IPlayerControlledEntity> entities = EntityManager.getInstance().getEntitiesOfPlayer(this);
		
		for(IPlayerControlledEntity entity: entities){
			float unitSize = entity.getSize();
			Position unitPos = entity.getPosition();
			
			
			//If the point is within the area of the unit
			if(isWithin(pos.getX(), unitPos.getX()-unitSize/2, unitPos.getY()+unitSize/2)
					&& isWithin(pos.getY(), unitPos.getY()-unitSize/2, unitPos.getY() + unitSize/2)){
				selectedEntities.add(entity);
				break;
				
			}
		}
	}
	
	private boolean isWithin(float p, float low, float high){
		return p>=low && p<=high;
	}

	@Override
	public void moveSelectedTo(Position p) {
		for(IEntity entity: selectedEntities){
			if(entity instanceof Unit){
				Unit unit = (Unit) entity;
				unit.moveTo(p);
			}
		}
	}

	@Override
	public List<IEntity> getSelectedEntities() {
		List<IEntity> entities = new ArrayList<IEntity>();
		for(IEntity entity: selectedEntities){
			entities.add(entity);
		}
		return entities;
	}
	
	/**
	 * Updates the player.
	 * @param tpf Time per frame
	 */
	public void update(float tpf)
	{
		// TODO Should the entity updates really ba handled by the Player? Not in EntityManager?
		// E.g. entities that are not controlled by a player can't be updated atm.
		List<IPlayerControlledEntity> entities = EntityManager.getInstance().getEntitiesOfPlayer(this);
		
		for(IEntity entity: entities){
			if(entity instanceof AbstractEntity){
				Unit unit = (Unit) entity;
				unit.update(tpf);
			}
			
		}
	}
	
	@Override
	public boolean equals(Object o){
		if(this==o){
			return true;
		} else if(o != null && o instanceof Player){
			Player otherPlayer = (Player) o;
			if(selectedEntities.equals(otherPlayer.selectedEntities)){
				return true;
			}
		}
		return false;
		
	}
}
