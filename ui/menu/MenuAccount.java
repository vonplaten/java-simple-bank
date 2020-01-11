package casvonp.ui.menu;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JMenuItem;



/**
 * MenuAccount holds JMenuItems for Account main menu.
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public class MenuAccount extends MenuBase {

    private static final long serialVersionUID = 1L;
    private JMenuItem createSaving;
    private JMenuItem createCredit;

    /**
     * Constructor MenuAccount
     * 
     * @param observer    ImenuObserver    The listener for changes in this menu.
     */
    public MenuAccount(IMenuObserver observer) {
        super("Konto", observer);

        // Add items to Customer menu
        createSaving = new JMenuItem("Skapa sparkonto");
        createSaving.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                casvonp.ui.Controller c = ( casvonp.ui.Controller)observer;
                customerCreateSavingEvent(new MenuAction(c.getCustomerPanel().getPno()));
            }
        });
        add(createSaving);

        createCredit = new JMenuItem("Skapa kreditkonto");
        createCredit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                casvonp.ui.Controller c = ( casvonp.ui.Controller)observer;
                String pno = c.getCustomerPanel().getPno();
                customerCreateCreditEvent(new MenuAction(pno));
            }
        });
        add(createCredit);


        createCredit = new JMenuItem("Skriv ut konto");
        createCredit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                casvonp.ui.Controller c = ( casvonp.ui.Controller)observer;
                String pno = c.getCustomerPanel().getPno();
                try{
                    int ano = Integer.parseInt(c.getAccountPanel().getAno());
                    accountCreateAccountFile(new MenuAction(pno, ano));
                } catch (Exception ex){
                    System.out.println(ex);
                    System.out.println("Välj ett konto att skriva ut.");
                }
            }
        });
        add(createCredit);
    }




}