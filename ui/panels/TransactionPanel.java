package casvonp.ui.panels;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;


/**
 * Panel containing transactions within an account
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public class TransactionPanel extends JPanel{
    private FormPanel parent;
    private JLabel trLabel;
    public JList trList;
    public DefaultListModel trListModel;

    public TransactionPanel(FormPanel parent){
        this.parent = parent;
        java.awt.Dimension dim = getPreferredSize();
        dim.width = FormPanel.width;
        dim.height = 170;
        setPreferredSize(dim);
        setLayout(new GridBagLayout());

        javax.swing.border.Border ib = BorderFactory.createTitledBorder(BorderFactory
        .createEtchedBorder(EtchedBorder.LOWERED), "Transaktioner");
        javax.swing.border.Border ob = BorderFactory.createEmptyBorder(5,5,5,5);
        javax.swing.border.Border cb = BorderFactory.createCompoundBorder(ob, ib);
        setBorder(cb);


        // Account list
        trLabel = new JLabel("Datum                          Belopp          Saldo");
        trLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        trListModel = new DefaultListModel();
        trList = new JList<String>(trListModel) {
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                Dimension size = super.getPreferredScrollableViewportSize();
                size.width = 300;
                size.height = 100;
                return size;
            }
        };
        trList.setFont(new Font("Serif", Font.PLAIN, 11));
        JScrollPane trsp = new JScrollPane(trList);


        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(trLabel, gc);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(trsp, gc);

    }
}