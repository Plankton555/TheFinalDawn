package projectrts.model.ai;

import java.util.List;

import projectrts.model.entities.EntityManager;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.abilities.AttackAbility;
import projectrts.model.player.IPlayer;

public class StrategicAI {
	private List<IPlayerControlledEntity> entities;
	private final float cooldownInterval = 0.5f;
	private float cooldownRemaining = 0;
	private IPlayer aiPlayer;
	
	public StrategicAI(IPlayer aiPlayer) {
		this.aiPlayer = aiPlayer;
	}
	
	public void update(float tpf) {
		if(cooldownRemaining <= 0) {
			entities = EntityManager.getInstance().getEntitiesOfPlayer(aiPlayer);
			for(IPlayerControlledEntity entity : entities) {
				if(entity.getState() == PlayerControlledEntity.State.IDLE) {
					if(EntityManager.getInstance().getClosestEnemyStructure(entity) != null) {
						entity.doAbility(AttackAbility.class.getSimpleName(), 
								EntityManager.getInstance().getClosestEnemyStructure(entity).getPosition());
					}
				}
			}
			cooldownRemaining = cooldownInterval;
		} else {
			cooldownRemaining -= tpf;
		}
	}
}
