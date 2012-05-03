package projectrts.model.entities;

import java.beans.PropertyChangeListener;

public interface IPlayer {

	public void addListener(PropertyChangeListener pcl);

	public int getResources();

}
