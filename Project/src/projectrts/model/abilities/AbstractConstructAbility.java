package projectrts.model.abilities;

import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Player;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.world.Position;
import projectrts.model.world.World;

/**
 * An ability for handling constructions of buildings
 * 
 * @author Jakob Svensson
 * 
 */
abstract class AbstractConstructAbility extends AbstractAbility implements
		IUsingMoveAbility, IBuildStructureAbility {
	private float buildTime;
	private int buildCost;
	private static float cooldown = 0.5f;
	private Position buildPos;
	private float buildTimeLeft;
	private AbstractAbility moveAbility;
	private float size;
	private String entityToTrain;
	private Player owner;

	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(PlayerControlledEntity entity,
			MoveAbility moveAbility) {
		this.entity = entity;
		this.setCooldown(cooldown);
		this.moveAbility = moveAbility;
	}

	@Override
	public String getName() {
		return AbstractConstructAbility.class.getSimpleName();
	}

	@Override
	public void update(float tpf) {
		if (isActive() && !isFinished()) {
			if (Position.getDistance(entity.getPosition(), buildPos) < size * 1.5) {
				// If in range of buildingPosition
				moveAbility.setFinished(true);
				if (buildTimeLeft <= 0) {
					EntityManager.INSTANCE.addNewPCE(entityToTrain,
							(Player) entity.getOwner(), buildPos);
					setFinished(true);
					buildTimeLeft = buildTime;
					pcs.firePropertyChange("BuildCompleted", entity, null);
				} else {
					pcs.firePropertyChange("BuildTimeLeft", entity,
							(int) (buildTimeLeft - tpf + 1));
					buildTimeLeft -= tpf;
				}
			} else {
				// Not in range
				if (!moveAbility.isActive()) {
					moveAbility.useAbility(buildPos);
				}
			}
		}
	}

	@Override
	public void useAbility(Position target) {
		owner = (Player) entity.getOwner();
		if (owner.getResources() >= buildCost) {
			owner.modifyResource(-buildCost);
			buildPos = target;
			setActive(true);
			setFinished(false);
			buildTimeLeft = buildTime;
			World.INSTANCE.setNodesOccupied(World.INSTANCE.getNodeAt(target),
					getSizeOfBuilding(),
					EntityManager.INSTANCE.requestNewEntityID());
		} else {
			pcs.firePropertyChange("NotEnoughResources", null, null);
		}
	}

	@Override
	public float getSizeOfBuilding() {
		return size;
	}

	protected void setSizeOfBuilding(float size) {
		this.size = size;
	}

	protected void setBuildTime(float buildTime) {
		this.buildTime = buildTime;
	}

	protected void setBuildCost(int buildCost) {
		this.buildCost = buildCost;
	}

	protected void setEntityToTrain(String name) {
		this.entityToTrain = name;
	}

	@Override
	public void abortAbility() {
		if (isActive()) {
			super.abortAbility();
			World.INSTANCE.setNodesOccupied(World.INSTANCE.getNodeAt(buildPos),
					getSizeOfBuilding(), 0);
			owner.modifyResource(buildCost);
			pcs.firePropertyChange("BuildCompleted", entity, null);
		}
	}

}
