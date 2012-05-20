package projectrts.model;

import projectrts.model.abilities.AttackAbility;
import projectrts.model.abilities.IAbilityManager;
import projectrts.model.entities.AbstractPlayerControlledEntity;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Worker;

/**
 * The "brain" of a unit.
 * 
 * @author Markus Ekström
 * 
 */
class MicroAI {
	private final AbstractPlayerControlledEntity myPCE;
	private AbstractPlayerControlledEntity target;
	private final IAbilityManager abilityManager;
	private final EntityManager entityManager;

	/**
	 * Creates a micro AI for the provided unit.
	 * 
	 * @param pce
	 *            The entity the ai will control.
	 * @param abilityManager
	 * @param unit
	 *            The unit
	 */
	public MicroAI(AbstractPlayerControlledEntity pce, IAbilityManager abilityManager) {
		this.myPCE = pce;
		this.abilityManager = abilityManager;
		this.entityManager = EntityManager.INSTANCE;
	}

	// TODO Markus: Add javadoc
	public void update(float tpf) {
		if (!entityManager.isSelected(myPCE)) {
			if (entityManager.getClosestEnemy(myPCE) == null) {
				target = null;
				abilityManager.abortAbility(
						AttackAbility.class.getSimpleName(), myPCE);
				myPCE.setState(AbstractPlayerControlledEntity.State.IDLE);
			} else {
				if (!EntityManager.INSTANCE.getClosestEnemy(myPCE).equals(
						target)
						&& myPCE.getClass() != Worker.class) {
					target = EntityManager.INSTANCE.getClosestEnemy(myPCE);
					abilityManager.doAbility(AttackAbility.class
							.getSimpleName(), EntityManager.INSTANCE
							.getClosestEnemy(myPCE).getPosition(), myPCE);
				}
			}
		}
	}

	// TODO Markus: Add javadoc
	public AbstractPlayerControlledEntity getEntity() {
		return myPCE;
	}
}