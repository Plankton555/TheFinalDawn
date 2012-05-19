package projectrts.model.abilities;

import projectrts.model.entities.AbstractUnit;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.world.Position;

/**
 * An ability for attacking
 * 
 * @author Filip Brynfors
 * 
 */
public class AttackAbility extends AbstractAbility implements
		IUsingMoveAbility, ITargetAbility {
	private PlayerControlledEntity target;

	private MoveAbility moveAbility;
	private double range;

	static {
		AbilityFactory.registerAbility(AttackAbility.class.getSimpleName(),
				new AttackAbility());
	}

	/**
	 * When subclassing, invoke this to initialize the ability.
	 */
	protected void initialize(PlayerControlledEntity entity,
			MoveAbility moveAbility) {
		this.entity = entity;
		this.moveAbility = moveAbility;
		this.setCooldown(0.5f);
	}

	@Override
	public String getName() {
		return "Attack";
	}

	@Override
	public void useAbility(Position pos) {
		target = EntityManager.INSTANCE.getPCEAtPosition(pos);

		if (target == null) {
			pcs.firePropertyChange("TargetNotPCE", null, null);
		} else {
			AbstractUnit au = (AbstractUnit) entity;
			range = au.getAttackRange();
			setActive(true);
			setFinished(false);
		}
	}

	@Override
	public void update(float tpf) {
		updateCooldown(tpf);

		if (isActive() && !isFinished()) {
			if (inRange(target)) {
				// In range
				moveAbility.abortAbility();
				if (getRemainingCooldown() <= 0) {

					dealDamage();
				}
			} else {
				// Out of range

				if (moveAbility.isActive()) {
					moveAbility.updateTarget(target.getPosition());
				} else {
					moveAbility.useAbility(target.getPosition());
				}
			}
		}
	}

	@Override
	public AbstractAbility createAbility(PlayerControlledEntity entity,
			MoveAbility moveAbility) {
		AttackAbility newAbility = new AttackAbility();
		newAbility.initialize(entity, moveAbility);
		return newAbility;
	}

	private boolean inRange(IPlayerControlledEntity target) {
		return (Position
				.getDistance(entity.getPosition(), target.getPosition()) < range
				+ (target.getSize() / 2) * 1.5);
	}

	private void dealDamage() {
		target.dealDamageTo(entity.getDamage());
		this.setAbilityUsed();

		if (target.getCurrentHealth() == 0) {
			this.setFinished(true);
		}
	}

	@Override
	public String getInfo() {
		return "Attacks an enemy";
	}
}