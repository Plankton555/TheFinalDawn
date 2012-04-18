package projectrts.model.core.abilities;

import javax.vecmath.Vector2d;

import projectrts.model.core.P;
import projectrts.model.core.Position;
import projectrts.model.core.entities.PlayerControlledEntity;
import projectrts.model.core.pathfinding.AStar;
import projectrts.model.core.pathfinding.AStarPath;
import projectrts.model.core.pathfinding.Node;
import projectrts.model.core.utils.ModelUtils;

/**
 * An ability for moving
 * @author Filip Brynfors, modified by Bjorn Persson Mattsson
 *
 */
public class MoveAbility extends AbstractAbility {
	private PlayerControlledEntity entity;
	private Position targetPosition;
	// TODO Plankton: Change nodes occupation when moving.
	private AStar aStar;
	private AStarPath path;
	private float pathRefreshInterval = 1; // refreshes path every second
	private float timeSincePathRefresh = pathRefreshInterval;
	
	static {
		AbilityFactory.INSTANCE.registerAbility(MoveAbility.class.getSimpleName(), new MoveAbility());
	}
	
	/**
	 * Creates a new instance of this ability.
	 */
	private MoveAbility(){
		
	}
	
	@Override
	public String getName() {
		return "Move";
	}
	
	@Override
	public void useAbility(PlayerControlledEntity entity, Position pos){
		this.entity = entity;
		this.targetPosition = pos;
		
		// Want to refresh path as soon as a click is made
		this.timeSincePathRefresh = pathRefreshInterval;
		
		setActive(true);
		setFinished(false);
	}

	@Override
	public void update(float tpf) {
		if(isActive() && !isFinished()){
			
			entity.setPosition(determineNextStep(tpf, entity, targetPosition));
			if (entity.getPosition().equals(targetPosition))
			{
				// TODO Plankton: This will probably never happen since A* goes to the position of
				// the closest node of targetPosition, and not targetPosition itself. Maybe use
				// the closest node's position instead?..
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
	private Position determineNextStep(float tpf, PlayerControlledEntity entity, Position targetPos)
	{
		double stepLength = P.INSTANCE.getUnitLength()*tpf;
		
		if (timeSincePathRefresh >= pathRefreshInterval)
		{
			path = aStar.calculatePath(entity.getPosition(), targetPos, entity.hashCode());
			timeSincePathRefresh = 0;
		}
		else
		{
			timeSincePathRefresh += tpf;
		}
		
		Position outputPos = entity.getPosition();
		
		while (stepLength > 0) // repeat until the whole step is taken (or no nodes are left in the path)
		{
			if (path.nrOfNodesLeft() < 1)
			{
				break;
			}
			Position nextNode = path.getNextNodePosition();
			double distanceToNextNode = ModelUtils.INSTANCE.getDistance(outputPos, nextNode);
			
			if (distanceToNextNode > stepLength)
			{
				Vector2d direction = Position.getVectorBetween(outputPos, nextNode);
				outputPos = outputPos.add(stepLength, direction);
				stepLength = 0;
			}
			else //if (distanceToNextNode <= stepLength)
			{
				stepLength -= distanceToNextNode;
				outputPos = nextNode;
				path.removeNodeFromPath();
			}
		}
		return outputPos;
	}
	
	@Override
	public AbstractAbility createAbility() {
		MoveAbility newAbility = new MoveAbility();
		newAbility.aStar = new AStar();
		return newAbility;
	}
}
