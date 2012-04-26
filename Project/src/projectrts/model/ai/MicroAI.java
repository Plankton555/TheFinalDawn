package projectrts.model.ai;

import projectrts.model.entities.EntityManager;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.abilities.AttackAbility;


/**
 * The "brain" of a unit.
 * @author Bjorn Persson Mattsson
 *
 */
public class MicroAI {
	private PlayerControlledEntity myPCE;
	
	/**
	 * Creates a micro AI for the provided unit.
	 * @param unit The unit
	 */
	public MicroAI(PlayerControlledEntity pce) {
		this.myPCE = pce;
	}
	
	public void update(float tpf) {
		if(!EntityManager.getInstance().isSelected(myPCE)) {
			if(EntityManager.getInstance().getClosestEnemy(myPCE) != null) {
				myPCE.doAbility(AttackAbility.class.getSimpleName(), EntityManager.getInstance().getClosestEnemy(myPCE).getPosition());
			}
		}
	}
	
	public PlayerControlledEntity getEntity() {
		return myPCE;
	}
}
