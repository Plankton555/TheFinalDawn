package projectrts.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import projectrts.model.abilities.IAbilityManager;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Player;
import projectrts.model.entities.PlayerControlledEntity;

public class AIManager implements PropertyChangeListener{
	private IAbilityManager abilityManager;
	private StrategicAI stratAI;
	private List<MicroAI> microAIs = new ArrayList<MicroAI>();
	
	public AIManager(Player aiPlayer, IAbilityManager abilityManager) {
		this.abilityManager = abilityManager;
		stratAI = new StrategicAI(aiPlayer, abilityManager);
		EntityManager.getInstance().addListener(this);
	}
	
	public void update(float tpf) {
		for (MicroAI mAI : microAIs) {
			mAI.update(tpf);
		}
		
		stratAI.update(tpf);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getOldValue() instanceof PlayerControlledEntity) {
			for(int i = 0; i < microAIs.size(); i++) {
				if(microAIs.get(i).getEntity().equals(evt.getOldValue())) {
					microAIs.remove(i);
				}
			}
		}
		
		if(evt.getNewValue() instanceof PlayerControlledEntity) {
			microAIs.add(new MicroAI((PlayerControlledEntity)evt.getNewValue(), abilityManager));
		}

	}

}
