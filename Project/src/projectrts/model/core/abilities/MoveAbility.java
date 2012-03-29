package projectrts.model.core.abilities;

import projectrts.model.core.AStar;
import projectrts.model.core.AStarPath;
import projectrts.model.core.P;
import projectrts.model.core.Position;
import projectrts.model.core.entities.PlayerControlledEntity;
import projectrts.model.core.utils.ModelUtils;

/**
 * An ability for attacking
 * @author Filip Brynfors, modified by Bjorn Persson Mattsson
 *
 */
public class MoveAbility extends AbstractAbility {
	private PlayerControlledEntity entity;
	private Position targetPosition;
	
	AStar aStar;
	AStarPath path;
	
	
	public MoveAbility(){

		this.aStar = new AStar();
	}
	
	@Override
	public String getName() {
		return "Move";
	}
	
	@Override
	public void useAbility(PlayerControlledEntity entity, Position pos){
		this.entity = entity;
		this.targetPosition = pos;
		
		//TODO: Are these needed?
		setActive(true);
		setFinished(false);
	}

	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			
			entity.setPosition(determinePath(targetPosition, tpf));
			if (entity.getPosition().equals(targetPosition))
			{
				setFinished(true);
			}
			
		}
	}
	
	/**
	 * Returns the position of the next step using A* algorithm.
	 * @param stepLength Length of the step the entity can take this update.
	 * @param entity The entity that's moving.
	 * @param targetPos The position that the entity will move towards.
	 * @return Position of next step.
	 */
	private Position determineNextStep(float stepLength, PlayerControlledEntity entity, Position targetPos)
	{
		// TODO Don't update the path every update.
		path = aStar.calculatePath(entity.getPosition(), targetPos);
		Position nextPos = path.getNextNodePosition();
		Position currentPos = entity.getPosition();
		float distanceToNextPos = ModelUtils.INSTANCE.getDistance(currentPos, nextPos);
		
		if (distanceToNextPos < stepLength)
		{
			stepLength -= distanceToNextPos;
			// TODO Plankton var här senast
		}
		return null;
	}

	private Position determinePath(Position target, float tpf){
		// TODO Extremely simple path algorithm
		float stepSize = P.INSTANCE.getUnitLength()*tpf;
		Position myPos = entity.getPosition();
		float newX = 0;
		float newY = 0;
		
		// For x axis
		if (Math.abs(myPos.getX() - target.getX()) < stepSize)
		{
			newX = target.getX();
		}
		else if (myPos.getX() < target.getX())
		{
			newX = myPos.getX()+stepSize;
		}
		else// if (myPos.getX() > target.getX())
		{
			newX = myPos.getX()-stepSize;
		}
		
		// For y axis
		if (Math.abs(myPos.getY() - target.getY()) < stepSize)
		{
			newY = target.getY();
		}
		else if (myPos.getY() < target.getY())
		{
			newY = myPos.getY()+stepSize;
		}
		else// if (myPos.getY() > target.getY())
		{
			newY = myPos.getY()-stepSize;
		}
		
		return new Position(newX, newY);
	}

}
