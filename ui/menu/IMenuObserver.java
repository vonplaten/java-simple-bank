package casvonp.ui.menu;

/**
 * Interface for menu listeners.
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public interface IMenuObserver{
    public void onCustomerDelete(MenuAction e);
    public void onCustomerSave(MenuAction e);
    public void onCreateSavingAccount(MenuAction e);
    public void onCreateCreditAccount(MenuAction e);
    public void onImport();
    public void onExport();
    public void onPopulate();
    public void onCreateAccountFile(MenuAction e);
}