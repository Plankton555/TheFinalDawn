package projectrts.model.core.abilities;


public abstract class AbstractAbility implements IAbility {
	private float cooldown;
	private float remainingCooldown = 0;
	
	public AbstractAbility(float cooldown){
		this.cooldown = cooldown;
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
	 * Updates the cooldown of the ability
	 */
	public void update(float tpf){
		if(remainingCooldown < tpf){
			remainingCooldown = 0;
		} else {
			remainingCooldown -= tpf;
		}
	}
	
	/**
	 * Updates sets the remaining cooldown to be the max cooldown
	 */
	protected void setAbilityUsed(){
		remainingCooldown = cooldown;
	}
	
	
	//TODO: Add this abstract method and update sub classes to implement this method
	//public abstract void useAbility(PlayerControlledEntity caster, Position target);

}
