package projectrts.model;

import java.util.List;

import projectrts.model.abilities.AbilityManager;
import projectrts.model.abilities.AttackAbility;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.entities.Player;
import projectrts.model.entities.PlayerControlledEntity;

public class StrategicAI {
	private List<IPlayerControlledEntity> entities;
	private final float cooldownInterval = 0.5f;
	private float cooldownRemaining = 0;
	private Player aiPlayer;
	private AbilityManager abilityManager;
	
	public StrategicAI(Player aiPlayer, AbilityManager abilityManager) {
		this.aiPlayer = aiPlayer;
		this.abilityManager = abilityManager;
	}
	
	public void update(float tpf) {
		if(cooldownRemaining <= 0) {
			entities = EntityManager.getInstance().getEntitiesOfPlayer(aiPlayer);
			for(IPlayerControlledEntity entity : entities) {
				if(entity instanceof PlayerControlledEntity) {
					PlayerControlledEntity pce = (PlayerControlledEntity) entity;
					if(pce.getState() == PlayerControlledEntity.State.IDLE) {
						if(EntityManager.getInstance().getClosestEnemyStructure(pce) != null) {
							abilityManager.doAbility(AttackAbility.class.getSimpleName(), 
									EntityManager.getInstance().getClosestEnemyStructure(pce).getPosition(), entity);
						}
					}
				}
			}
			cooldownRemaining = cooldownInterval;
		} else {
			cooldownRemaining -= tpf;
		}
	}
}
