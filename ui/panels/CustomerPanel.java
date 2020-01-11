package casvonp.ui.panels;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Panel containing all customer UI elements
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public class CustomerPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private FormPanel parent;
    private JLabel pnoLabel;
    protected JTextField pnoField;
    private JButton pnoFindButton;
    private JLabel fnameLabel;
    protected JTextField fnameField;
    private JLabel lnameLabel;
    protected JTextField lnameField;
    // private JButton saveButton;
    // private JButton deleteButton;
    

    public CustomerPanel(FormPanel parent) {
        this.parent = parent;
        java.awt.Dimension dim = getPreferredSize();
        dim.width = FormPanel.width;
        dim.height = 150;
        setPreferredSize(dim);
        setLayout(new GridBagLayout());

        javax.swing.border.Border ib = BorderFactory.createTitledBorder(BorderFactory
        .createEtchedBorder(EtchedBorder.LOWERED), "Kund");
        javax.swing.border.Border ob = BorderFactory.createEmptyBorder(5,5,5,5);
        javax.swing.border.Border cb = BorderFactory.createCompoundBorder(ob, ib);
        setBorder(cb);

        pnoLabel = new JLabel("Pno: ");
        pnoLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        pnoField = new JTextField(10);
        pnoFindButton = new JButton("Hämta");
        pnoFindButton.setFont(new Font("Serif", Font.PLAIN, 10));
        fnameLabel = new JLabel("Förnamn: ");
        fnameLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        fnameField = new JTextField(10);
        lnameLabel = new JLabel("Efternamn: ");
        lnameLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        lnameField = new JTextField(10);
        // saveButton = new JButton("Spara");
        // saveButton.setFont(new Font("Serif", Font.PLAIN, 10));
        // deleteButton = new JButton("Ta bort");
        // deleteButton.setFont(new Font("Serif", Font.PLAIN, 10));

        GridBagConstraints gc = new GridBagConstraints();

        /// PNO ///
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(pnoLabel, gc);
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(pnoField, gc);
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 2;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(pnoFindButton, gc);

        /// first name ///
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(fnameLabel, gc);
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(fnameField, gc);

        /// last name ///
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(lnameLabel, gc);
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(lnameField, gc);

        /// radera spara ///
        // gc.weightx = 1;
        // gc.weighty = 2;
        // gc.gridx = 1;
        // gc.gridy = 3;
        // gc.anchor = GridBagConstraints.FIRST_LINE_END;
        // add(deleteButton, gc);
        // gc.weightx = 1;
        // gc.weighty = 2;
        // gc.gridx = 2;
        // gc.gridy = 3;
        // gc.anchor = GridBagConstraints.FIRST_LINE_START;
        // add(saveButton, gc);


        pnoFindButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String pno = pnoField.getText();
                String fname = fnameField.getText();
                String lname = lnameField.getText();
                FormPanelAction fpe = new FormPanelAction(pno, fname, lname);
                parent.fireFindEvent(fpe);
            }
        });
        // saveButton.addActionListener(new ActionListener(){
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         String pno = pnoField.getText();
        //         String fname = fnameField.getText();
        //         String lname = lnameField.getText();
        //         FormPanelAction fpe = new FormPanelAction(pno, fname, lname);
        //         parent.fireChangeNameEvent(fpe);
        //     }
        // });
        // deleteButton.addActionListener(new ActionListener(){
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         String pno = pnoField.getText();
        //         FormPanelAction fpe = new FormPanelAction(pno, null, null);
        //         parent.fireDeleteCustomerEvent(fpe);
        //     }
        // });

    }

    public String getPno(){
        return this.pnoField.getText();
    }
    public String getFname(){
        return this.fnameField.getText();
    }
    public String getLname(){
        return this.lnameField.getText();
    }
}
