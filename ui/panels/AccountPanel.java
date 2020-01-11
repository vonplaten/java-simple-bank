package casvonp.ui.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 * Panel containing all account UI elements
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public class AccountPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private String ano;

    private FormPanel parent;
    private JLabel accLabel;
    public JList accList;
    public DefaultListModel accNrListModel;
    //private JButton accFindButton;
    private JLabel nrLabel;
    public JTextField nrInfo;
    private JLabel typeLabel;
    public JTextField typeInfo;
    private JLabel amountLabel;
    public JTextField amountInfo;
    private JLabel rateLabel;
    public JTextField rateInfo;
    private JLabel interestLabel;
    public JTextField interestInfo;
    private JButton closeButton;
    private JLabel transactLabel;
    public JTextField transactInfo;
    private JButton transactButton;
    // private JLabel withdrawLabel;
    // public JTextField withdrawInfo;
    // private JButton withdrawButton;

    public AccountPanel(FormPanel parent) {
        this.parent = parent;
        java.awt.Dimension dim = getPreferredSize();
        dim.width = FormPanel.width;
        dim.height = 250;
        setPreferredSize(dim);
        setLayout(new GridBagLayout());

        javax.swing.border.Border ib = BorderFactory.createTitledBorder(BorderFactory
        .createEtchedBorder(EtchedBorder.LOWERED), "Konton");
        javax.swing.border.Border ob = BorderFactory.createEmptyBorder(5,5,5,5);
        javax.swing.border.Border cb = BorderFactory.createCompoundBorder(ob, ib);
        setBorder(cb);


        // Account list
        accLabel = new JLabel("Konton: ");
        accLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        accNrListModel = new DefaultListModel();
        accList = new JList<String>(accNrListModel) {
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                Dimension size = super.getPreferredScrollableViewportSize();
                size.width = 100;
                size.height = 50;
                return size;
            }
        };
        accList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String nr = (String) accList.getSelectedValue();
                    parent.setSelectedAccount(nr);
                    ano = nr;
                }
            }
        });
        accList.setFont(new Font("Serif", Font.PLAIN, 12));
        JScrollPane asp = new JScrollPane(accList);
        
        //Nr
        nrLabel = new JLabel("Kontonr: ");
        nrLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        nrInfo = new JTextField(12);

        //Rate
        rateLabel = new JLabel("Ränta: ");
        rateLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        rateInfo = new JTextField(12);
        //Amount
        amountLabel = new JLabel("Saldo: ");
        amountLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        amountInfo = new JTextField(12);
        //Type
        typeLabel = new JLabel("Kontotyp: ");
        typeLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        typeInfo = new JTextField(12);
        //Interest
        interestLabel = new JLabel("Erhållen ränta: ");
        interestLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        interestInfo = new JTextField(12);

        //Close button
        closeButton = new JButton("Avsluta konto");
        closeButton.setFont(new Font("Serif", Font.PLAIN, 9));
        closeButton.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent arg0) {
                FormPanelAction fpa = new FormPanelAction(parent.getPno(), ano);
                parent.fireCloseAccountEvent(fpa);
            }
        });

        //transact
        transactLabel = new JLabel("Transaktion:");
        transactLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        transactInfo = new JTextField(12);
        transactButton = new JButton("Sätt in / ta ut");
        transactButton.setFont(new Font("Serif", Font.PLAIN, 9));
        transactButton.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent arg0) {
                double transactAmount = 0;
                try{
                    transactAmount = Integer.parseInt(transactInfo.getText());
                } catch (Exception e) {
                    System.out.println("Can not parse: " + transactInfo.getText());
                }
                if (transactAmount != 0){
                    FormPanelAction fpa = new FormPanelAction(parent.getPno(), ano, transactAmount);
                    parent.fireTransactAccountEvent(fpa);
                }
            }
        });

        // //withdraw
        // withdrawLabel = new JLabel("Uttag");
        // withdrawLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        // withdrawInfo = new JTextField(12);
        // withdrawButton = new JButton("Ta ut");
        // withdrawButton.setFont(new Font("Serif", Font.PLAIN, 9));

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.2;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(accLabel, gc);
        gc.weightx = 10;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(asp, gc);

        // details
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(nrLabel, gc);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(nrInfo, gc);

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(typeLabel, gc);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(typeInfo, gc);

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(amountLabel, gc);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(amountInfo, gc);
        
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(rateLabel, gc);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(rateInfo, gc);

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(transactLabel, gc);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(transactInfo, gc);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 2;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(transactButton, gc);

        // gc.weightx = 1;
        // gc.weighty = 1;
        // gc.gridx = 0;
        // gc.gridy = 6;
        // gc.anchor = GridBagConstraints.FIRST_LINE_START;
        // add(withdrawLabel, gc);
        // gc.weightx = 1;
        // gc.weighty = 1;
        // gc.gridx = 1;
        // gc.gridy = 6;
        // gc.anchor = GridBagConstraints.FIRST_LINE_START;
        // add(withdrawInfo, gc);
        // gc.weightx = 1;
        // gc.weighty = 1;
        // gc.gridx = 2;
        // gc.gridy = 6;
        // gc.anchor = GridBagConstraints.FIRST_LINE_START;
        // add(withdrawButton, gc);

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 7;
        gc.anchor = GridBagConstraints.LINE_START;
        add(closeButton, gc);

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 8;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(interestLabel, gc);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 8;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(interestInfo, gc);




    }

    public String getAno(){
        return this.nrInfo.getText();
    }

}
