package casvonp.ui.menu;


import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * Main menu bar for base navigation.
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public class MenuBar extends JMenuBar {

    private static final long serialVersionUID = 1L;
    private JMenu menuFile;
    private JMenu menuCustomer;
    private JMenu menuAccount;

    /**
     * Constructor MenuBar
     * 
     * @param observer    ImenuObserver    The listener for changes in all menus.
     */
    public MenuBar(IMenuObserver observer){
        menuFile = new MenuFile(observer);
        add(menuFile);

        menuCustomer = new MenuCustomer(observer);
        add(menuCustomer);
        
        menuAccount = new MenuAccount(observer);
        add(menuAccount);


    }
}