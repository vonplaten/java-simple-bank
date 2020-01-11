package casvonp.ui.menu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;


/**
 * Abstract baseclass for main menus.
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public abstract class MenuBase extends JMenu{

    private static final long serialVersionUID = 1L;
    private List<IMenuObserver> observers;

    /**
     * Constructor MenuBase
     * 
     * @param observer    ImenuObserver    The listener for changes in all menus.
     */
    public MenuBase(String name, IMenuObserver observer){
        super(name);
        observers = new ArrayList<>(25);
        observers.add(observer);

    }
    protected void customerDeleteEvent(MenuAction e) {
        for (IMenuObserver observer : observers) {
            observer.onCustomerDelete(e);
        }        
    }
    protected void customerSaveEvent(MenuAction e) {
        for (IMenuObserver observer : observers) {
            observer.onCustomerSave(e);
        }        
    }

    protected void customerCreateSavingEvent(MenuAction e){
        for (IMenuObserver observer : observers) {
            observer.onCreateSavingAccount(e);
        }        
    }
    protected void customerCreateCreditEvent(MenuAction e){
        for (IMenuObserver observer : observers) {
            observer.onCreateCreditAccount(e);
        }        
    }
    protected void accountCreateAccountFile(MenuAction e){
        for (IMenuObserver observer : observers) {
            observer.onCreateAccountFile(e);
        }        
    }

    protected void exportEvent(){
        for (IMenuObserver observer : observers) {
            observer.onExport();
        }
    }
    protected void importEvent(){
        for (IMenuObserver observer : observers) {
            observer.onImport();
        }
    }
    protected void populateEvent(){
        for (IMenuObserver observer : observers) {
            observer.onPopulate();
        }
    }
    

}