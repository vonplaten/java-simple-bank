package casvonp.ui.panels;

/**
 * Interface for View listeners
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public interface IFormPanelObserver{
    public void onFindCustomer(FormPanelAction e);
    public void onCloseAccount(FormPanelAction e);
    public void onTransactAccount(FormPanelAction e);
}