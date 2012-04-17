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
		resources=0; //TODO Anyone: Decide starting amount of resources
	}
	
	@Override
	public void select(Position pos) {
		//TODO Anyone: Add support for selection of multiple units and enemy units
		selectedEntities.clear();
		PlayerControlledEntity entity = ModelUtils.INSTANCE.getPlayerControlledEntityAtPosition(pos);
		if(entity!=null){ //No entity is at that position
			selectedEntities.add(entity);
			if(entity.getName().equals("Worker")){
				entity.doAbility("GatherResource", new Position(40, 50));
			}
			if(entity.getName().equals("Headquarter")){
				entity.doAbility("TrainWorker", new Position(40, 50));
			}
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
	
	// TODO Jakob: ADD JAVADOC
	public int getResource(){
		return resources; 
	}
	
	public void modifyResource(int amount){
		resources+=amount;
		System.out.println(resources);
		System.out.println(EntityManager.getInstance().getAllEntities());
	}
	
}
