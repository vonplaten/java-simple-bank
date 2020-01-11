package casvonp.ui.model;

/**
 * Interface for Model listeners. For example the Controller listens for changes in model.
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */
public interface IModelObserver{
    public void onModelEvent(ModelEvent e);
}