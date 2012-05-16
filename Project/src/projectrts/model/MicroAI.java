package projectrts.model;

import projectrts.model.abilities.AttackAbility;
import projectrts.model.abilities.IAbility;
import projectrts.model.abilities.IAbilityManager;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.PlayerControlledEntity;


/**
 * The "brain" of a unit.
 * @author Markus Ekström
 *
 */
public class MicroAI {
	// TODO Markus: PMD: Private field 'myPCE' could be made final; it is only initialized in the declaration or constructor.
	private PlayerControlledEntity myPCE;
	private PlayerControlledEntity target;
	// TODO Markus: PMD: Private field 'abilityManager' could be made final; it is only initialized in the declaration or constructor.
	private IAbilityManager abilityManager;
	private EntityManager entityManager;
	
	/**
	 * Creates a micro AI for the provided unit.
	 * @param pce The entity the ai will control.
	 * @param abilityManager 
	 * @param unit The unit
	 */
	public MicroAI(PlayerControlledEntity pce, IAbilityManager abilityManager) {
		this.myPCE = pce;
		this.abilityManager = abilityManager;
		this.entityManager = EntityManager.INSTANCE;
	}
	
	public void update(float tpf) {
		if(!entityManager.isSelected(myPCE)) {
			if(entityManager.getClosestEnemy(myPCE) == null) {
				target = null;
				abilityManager.abortAbility(AttackAbility.class.getSimpleName(), myPCE);
				myPCE.setState(PlayerControlledEntity.State.IDLE);
			} else {
				if(!EntityManager.INSTANCE.getClosestEnemy(myPCE).equals(target)) {
					target = EntityManager.INSTANCE.getClosestEnemy(myPCE);
					abilityManager.doAbility(AttackAbility.class.getSimpleName(), 
							EntityManager.INSTANCE.getClosestEnemy(myPCE).getPosition(), myPCE);
				}
			}
		}
	}
	
	public PlayerControlledEntity getEntity() {
		return myPCE;
	}
}
