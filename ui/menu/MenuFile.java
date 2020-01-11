package casvonp.ui.menu;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JMenuItem;



/**
  * Menu (JMenu) for File main menu.
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public class MenuFile extends MenuBase {

    private static final long serialVersionUID = 1L;
    private JMenuItem exportData;
    private JMenuItem importData;

    /**
     * Constructor MenuFile
     * 
     * @param observer    ImenuObserver    The listener for changes in this menu.
     */
    public MenuFile(IMenuObserver observer) {
        super("Fil", observer);

        // Add items to Customer menu
        exportData = new JMenuItem("Exportera...");
        exportData.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                exportEvent();
            }
        });
        add(exportData);

        importData = new JMenuItem("Importera...");
        importData.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                importEvent();
            }
        });
        add(importData);

        importData = new JMenuItem("Fyll exempeldata");
        importData.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                populateEvent();
            }
        });
        add(importData);

    }




}