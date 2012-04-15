package projectrts.view.controls;

import java.util.HashMap;

import projectrts.model.core.entities.IEntity;

public enum ControlFactory {INSTANCE;
	
	
	private HashMap<String, AbstractControl> controlMap = new HashMap<String, AbstractControl>();

	public void registerControl(String controlType, AbstractControl control) {
		controlMap.put(controlType, control);
	}

	public AbstractControl createControl(String controlType, IEntity entity) {
		return controlMap.get(controlType).createControl(entity);
	}
}
