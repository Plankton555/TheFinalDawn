package projectrts.model.core.utils;

import java.util.List;

import projectrts.model.core.EntityManager;
import projectrts.model.core.Position;
import projectrts.model.core.entities.IEntity;
import projectrts.model.core.entities.IPlayerControlledEntity;
import projectrts.model.core.entities.NonPlayerControlledEntity;
import projectrts.model.core.entities.PlayerControlledEntity;

/**
 * Utility class
 * @author Filip Brynfors Modified by Jakob Svensson 
 *
 */
public enum ModelUtils {
	INSTANCE;
	
	/**
	 * Gets the distance between two positions
	 * @param p1 the first point
	 * @param p2 the second point
	 * @return the distance between the points
	 */
	public float getDistance(Position p1, Position p2){
		float dx = p1.getX() - p2.getX();
		float dy = p1.getY() - p2.getY();
		
		return (float) Math.sqrt(dx*dx+dy*dy);
	}
	
	
	//TODO: Should this method be in EntityManager?
	public PlayerControlledEntity getPlayerControlledEntityAtPosition(Position pos){
		List<IEntity> entities = EntityManager.getInstance().getAllEntities();
		for(IEntity entity: entities){
			if(entity instanceof PlayerControlledEntity){
				
				float unitSize = entity.getSize();
				Position unitPos = entity.getPosition();
				
				//If the point is within the area of the unit
				if(isWithin(pos.getX(), unitPos.getX()-unitSize/2, unitPos.getX()+unitSize/2)
						&& isWithin(pos.getY(), unitPos.getY()-unitSize/2, unitPos.getY() + unitSize/2)){
					PlayerControlledEntity pcEntity  = (PlayerControlledEntity) entity; 
					return pcEntity;
					
				}
			}
		}
		return null;
		
	}
	
	//TODO: Extraxt common code from getPlayerControlledEntityAtPosition and this method
	public NonPlayerControlledEntity getNonPlayerControlledEntity (Position pos){
		List<IEntity> entities = EntityManager.getInstance().getAllEntities();
		for(IEntity entity: entities){
			if(entity instanceof NonPlayerControlledEntity){
				
				float unitSize = entity.getSize();
				Position unitPos = entity.getPosition();
				
				//If the point is within the area of the unit
				if(isWithin(pos.getX(), unitPos.getX()-unitSize/2, unitPos.getX()+unitSize/2)
						&& isWithin(pos.getY(), unitPos.getY()-unitSize/2, unitPos.getY() + unitSize/2)){
					NonPlayerControlledEntity npcEntity  = (NonPlayerControlledEntity) entity; 
					return npcEntity;
					
				}
			}
		}
		return null;
	}
	
	public boolean isWithin(float p, float low, float high){
		return (p>=low && p<=high);
	}
}
