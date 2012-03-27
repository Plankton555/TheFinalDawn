package projectrts.model.core.abilities;

import projectrts.model.core.Position;
import projectrts.model.core.entities.PlayerControlledEntity;


public abstract class AbstractAbility implements IAbility {
	private boolean isFinished = false;
	private boolean isActive = false;
	private float cooldown;
	private float remainingCooldown = 0;
	
	public AbstractAbility(float cooldown){
		this.cooldown = cooldown;
	}
	
	public AbstractAbility(){
		this.cooldown = 0;
	}

	@Override
	public float getCooldown() {
		return cooldown;
	}

	@Override
	public float getRemainingCooldown() {
		return remainingCooldown;
	}
	
	/**
	 * Updates the ability
	 */
	public abstract void update(float tpf);
	
	
	protected void updateCooldown(float tpf){
		if(remainingCooldown < tpf){
			remainingCooldown = 0;
		} else {
			remainingCooldown -= tpf;
		}
	}
	
	public boolean isFinished(){
		return isFinished;
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public void setActive(boolean b){
		isActive = b;
	}
	
	public void setFinished(boolean b){
		isFinished = b;
	}
	
	/**
	 * Updates sets the remaining cooldown to be the max cooldown
	 */
	protected void setAbilityUsed(){
		remainingCooldown = cooldown;
	}
	
	
	public abstract void useAbility(PlayerControlledEntity caster, Position target);

}
