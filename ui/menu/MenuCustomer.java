package casvonp.ui.menu;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JMenuItem;



/**
  * Menu (JMenu) for Customer.
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public class MenuCustomer extends MenuBase {

    private static final long serialVersionUID = 1L;
    private JMenuItem delete;
    private JMenuItem save;

    /**
     * Constructor MenuCustomer
     * 
     * @param observer    ImenuObserver    The listener for changes in this menu.
     */
    public MenuCustomer(IMenuObserver observer) {
        super("Kund", observer);

        // Add items to Customer menu

        save = new JMenuItem("Spara kundnamn");
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                casvonp.ui.Controller c = ( casvonp.ui.Controller)observer;
                String pno = c.getCustomerPanel().getPno();
                String fname = c.getCustomerPanel().getFname();
                String lname = c.getCustomerPanel().getLname();
                customerSaveEvent(new MenuAction(pno, fname, lname));
            }
        });
        add(save);


        delete = new JMenuItem("Ta bort");
        delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                casvonp.ui.Controller c = ( casvonp.ui.Controller)observer;
                customerDeleteEvent(new MenuAction(c.getCustomerPanel().getPno()));
            }
        });
        add(delete);
    }
}