package projectrts.model.core;

import java.util.ArrayList;
import java.util.List;

import projectrts.model.core.entities.IEntity;
import projectrts.model.core.entities.PlayerControlledEntity;
import projectrts.model.core.utils.ModelUtils;

/**
 * Player class for handling all of one players units.
 * @author Björn Persson Mattson, Modified by Filip Brynfors, Jakob Svensson
 */
public class Player implements IPlayer {

	private List<PlayerControlledEntity> selectedEntities = new ArrayList<PlayerControlledEntity>();
	private int resources;
	
	/**
	 * Constructs a player
	 */
	public Player(){
		// TODO Anyone: Remove this test unit?
		//units.add(new Unit(new Position(10,10), this));
		resources=0; //TODO Anyone: Decide starting amount of resources
	}
	
	@Override
	public void select(Position pos) {
		//TODO Anyone: Add support for selection of multiple units and enemy units
		selectedEntities.clear();
		
		/*
		List<IPlayerControlledEntity> entities = EntityManager.getInstance().getEntitiesOfPlayer(this);
		for(IPlayerControlledEntity entity: entities){
			float unitSize = entity.getSize();
			Position unitPos = entity.getPosition();
			
			//If the point is within the area of the unit
			if(isWithin(pos.getX(), unitPos.getX()-unitSize/2, unitPos.getX()+unitSize/2)
					&& isWithin(pos.getY(), unitPos.getY()-unitSize/2, unitPos.getY() + unitSize/2)){
				selectedEntities.add(entity);
				break;
				
			}
		}
		*/
		PlayerControlledEntity entity = ModelUtils.INSTANCE.getPlayerControlledEntityAtPosition(pos);
		if(entity!=null){ //No entity is at that position
			selectedEntities.add(entity);
		}
		
	}
	

	@Override
	public void useAbilitySelected(String ability, Position p){
		for(IEntity entity: selectedEntities){
			if(entity instanceof PlayerControlledEntity){
				PlayerControlledEntity unit = (PlayerControlledEntity) entity;
				unit.doAbility(ability, p);
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
	
	public int getResource(){
		return resources; 
	}
	
	public void modifyResource(int amount){
		resources+=amount;
	}
	
}
