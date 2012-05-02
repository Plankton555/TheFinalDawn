package projectrts.model.ai;

import java.util.List;

import projectrts.model.abilities.AttackAbility;
import projectrts.model.abilities.IAbilityManager;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.player.IPlayer;

public class StrategicAI {
	private List<IPlayerControlledEntity> entities;
	private final float cooldownInterval = 0.5f;
	private float cooldownRemaining = 0;
	private IPlayer aiPlayer;
	private IAbilityManager abilityManager;
	
	public StrategicAI(IPlayer aiPlayer, IAbilityManager abilityManager) {
		this.aiPlayer = aiPlayer;
		this.abilityManager = abilityManager;
	}
	
	public void update(float tpf) {
		if(cooldownRemaining <= 0) {
			entities = EntityManager.getInstance().getEntitiesOfPlayer(aiPlayer);
			for(IPlayerControlledEntity entity : entities) {
				if(entity.getState() == PlayerControlledEntity.State.IDLE) {
					if(EntityManager.getInstance().getClosestEnemyStructure(entity) != null) {
						abilityManager.doAbility(AttackAbility.class.getSimpleName(), 
								EntityManager.getInstance().getClosestEnemyStructure(entity).getPosition(), entity);
					}
				}
			}
			cooldownRemaining = cooldownInterval;
		} else {
			cooldownRemaining -= tpf;
		}
	}
}
