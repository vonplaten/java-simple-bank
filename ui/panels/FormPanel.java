package casvonp.ui.panels;


import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import casvonp.ui.model.AccountModel;
import casvonp.ui.model.CustomerModel;
import casvonp.ui.model.TransactionModel;

/**
 * Only a container for other panels. This panel is the base panel inside a frame.
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public class FormPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    public static int width = 380;
    private List<IFormPanelObserver> observers;
    private CustomerModel model;
    private CustomerPanel customerPanel;
    private AccountPanel accountPanel;
    private TransactionPanel transactionPanel;
    

    public FormPanel(IFormPanelObserver observer){
        observers = new ArrayList<>(25);
        observers.add(observer);
        java.awt.Dimension dim = getPreferredSize();
        dim.width = width;
        setPreferredSize(dim);

        customerPanel = new CustomerPanel(this);
        add(customerPanel);
        accountPanel = new AccountPanel(this);
        add(accountPanel);
        transactionPanel = new TransactionPanel(this);
        add(transactionPanel);
    }

    protected void fireFindEvent(FormPanelAction e) {
        for (IFormPanelObserver observer : observers) {
            observer.onFindCustomer(e);
        }        
    }
    // protected void fireChangeNameEvent(FormPanelAction e) {
    //     for (IFormPanelObserver observer : observers) {
    //         observer.onSaveCustomer(e);
    //     }        
    // }
    // protected void fireDeleteCustomerEvent(FormPanelAction e){
    //     for (IFormPanelObserver observer : observers) {
    //         observer.onDeleteCustomer(e);
    //     }        
    // }
    public void fireModelEvent(FormPanelAction e){
        this.customerPanel.fnameField.setText(e.getFname());
        this.customerPanel.lnameField.setText(e.getLname());
    }
    public void fireCloseAccountEvent(FormPanelAction e){
        for (IFormPanelObserver observer : observers) {
            observer.onCloseAccount(e);
        }        
    }
    public void fireTransactAccountEvent(FormPanelAction e){
        for (IFormPanelObserver observer : observers) {
            observer.onTransactAccount(e);
        }        
    }

    public void updatePanelData(CustomerModel m){
        this.model = m;
        this.customerPanel.pnoField.setText(model.getPno());
        this.customerPanel.fnameField.setText(model.getFname());
        this.customerPanel.lnameField.setText(model.getLname());

        //Account clear
        this.accountPanel.accNrListModel.clear(); //clear
        this.accountPanel.nrInfo.setText(""); //clear
        this.accountPanel.amountInfo.setText("");
        this.accountPanel.rateInfo.setText("");
        this.accountPanel.typeInfo.setText("");
        this.accountPanel.interestInfo.setText("");
        this.accountPanel.transactInfo.setText("");

        //Transaction clear
        this.transactionPanel.trListModel.clear();

        // fill accounts
        for (AccountModel am : model.getAccounts()){
            if (!am.isClosed()){
                String ano = am.getNr();
                this.accountPanel.accNrListModel.addElement(ano);
                if (am.isSelected()){
                    this.accountPanel.accList.setSelectedValue(ano, true);
                    accountPanel.nrInfo.setText(am.getNr());
                    this.accountPanel.typeInfo.setText(am.getType());
                    this.accountPanel.rateInfo.setText(am.getRate());
                    this.accountPanel.amountInfo.setText(am.getAmount());

                    // fill transactions
                    for (TransactionModel tm : am.getTransactions()){
                        String tdate = tm.getDate();
                        String amount = tm.getAmount();
                        String holding = tm.getAccount_amount();
                        this.transactionPanel.trListModel.addElement(tdate + "        " + amount + "        " + holding);
                    }
                }
            }
            else if (am.isClosed()){
                this.accountPanel.interestInfo.setText(am.getInterest());
            }
            this.repaint();
        }

    }

    protected String getPno(){
        return this.customerPanel.getPno();
    }

    public void setSelectedAccount(String anr){
        for (AccountModel am : model.getAccounts()){
            if (am.getNr().equals(anr))
                am.setSelected(true);
            else
                am.setSelected(false);
        }
        updatePanelData(this.model);
    }

    public CustomerPanel getCustomerPanel(){
        return this.customerPanel;
    }
    public AccountPanel getAccountPanel(){
        return this.accountPanel;
    }


}