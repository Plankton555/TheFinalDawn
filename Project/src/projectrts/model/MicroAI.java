package projectrts.model;

import projectrts.model.abilities.AttackAbility;
import projectrts.model.abilities.IAbilityManager;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.PlayerControlledEntity;


/**
 * The "brain" of a unit.
 * @author Bjorn Persson Mattsson
 *
 */
public class MicroAI {
	private PlayerControlledEntity myPCE;
	private PlayerControlledEntity target;
	private IAbilityManager abilityManager;
	
	/**
	 * Creates a micro AI for the provided unit.
	 * @param pce The entity the ai will control.
	 * @param abilityManager 
	 * @param unit The unit
	 */
	public MicroAI(PlayerControlledEntity pce, IAbilityManager abilityManager) {
		this.myPCE = pce;
		this.abilityManager = abilityManager;
	}
	
	public void update(float tpf) {
		if(!EntityManager.getInstance().isSelected(myPCE)) {
			if(EntityManager.getInstance().getClosestEnemy(myPCE) != null) {
				if(!EntityManager.getInstance().getClosestEnemy(myPCE).equals(target)) {
					target = EntityManager.getInstance().getClosestEnemy(myPCE);
					abilityManager.doAbility(AttackAbility.class.getSimpleName(), 
							EntityManager.getInstance().getClosestEnemy(myPCE).getPosition(), myPCE);
				}
			}
		}
	}
	
	public PlayerControlledEntity getEntity() {
		return myPCE;
	}
}
