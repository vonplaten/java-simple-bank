
package casvonp.ui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;


import casvonp.ui.menu.IMenuObserver;
import casvonp.ui.menu.MenuBar;
import casvonp.ui.menu.MenuAction;
import casvonp.ui.model.AccountModel;
import casvonp.ui.model.CustomerModel;
import casvonp.ui.model.IModelObserver;
import casvonp.ui.model.ModelServiceProvider;
import casvonp.ui.model.TransactionModel;
import casvonp.ui.model.ModelEvent;
import casvonp.ui.panels.AccountPanel;
import casvonp.ui.panels.CustomerPanel;
import casvonp.ui.panels.FormPanel;
import casvonp.ui.panels.FormPanelAction;
import casvonp.ui.panels.IFormPanelObserver;
import casvonp.ui.panels.ListPanel;

/**
 * Main controller of the app. Acts between the View and the Model.
 * The view knows nothing about the model and vice versa.
 * All logic is implemented inside this controller.
 * 
 * This controller instantiates the view and model and manage both.
 * 
 * @author Casimir von Platen 
 * @version 0.1
 */
public class Controller extends JFrame implements IMenuObserver, IFormPanelObserver, IModelObserver{

    private static final long serialVersionUID = 1L;
    private MenuBar menuBar;
    private FormPanel fPanel;
    private ListPanel lPanel;
    private ModelServiceProvider model;

    public Controller() {
        super("BankApp");
        this.model = new ModelServiceProvider();
        this.model.addObserver(this);

        this.setLayout(new BorderLayout());
        menuBar = new MenuBar(this);
        setJMenuBar(menuBar);

        fPanel = new FormPanel(this);
        lPanel = new ListPanel();

        add(fPanel, BorderLayout.WEST);
        add(lPanel, BorderLayout.CENTER);

        this.setSize(700, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    /**
     * All listener methods from Menu
     * 
     */
    @Override
    public void onCustomerDelete(MenuAction e) {
        String pno = e.getPno();
        model.deleteCustomer(pno);
    }
    @Override
    public void onCustomerSave(MenuAction e) {
        String pno = e.getPno();
        String fname = e.getFname();
        String lname = e.getLname();
        this.model.changeCustomerName(pno, fname, lname);
    }
    @Override
    public void onCreateSavingAccount(MenuAction e) {
        String pno = e.getPno();
        model.createSavingAccount(pno);
    }
    @Override
    public void onCreateCreditAccount(MenuAction e) {
        String pno = e.getPno();
        model.createCreditAccount(pno);
    }
    @Override
    public void onExport() {
        model.bankexport();
    }
    @Override
    public void onImport() {
        model.bankimport();
    }
    @Override
    public void onPopulate() {
        model.bankpopulate();
    }

    /**
     * Serialize bank to casvonp_Files/bank.bin
     * 
     * @param MenuAction Takes param customernumber and accountnumber inside MenuAction
     * Writes accountreport to casvonp_Files/kontoutdrag_1001.txt where 1001 is accountnr.
     */
    @Override
    public void onCreateAccountFile(MenuAction e){
        StringBuilder sb = new StringBuilder();
        sb.append("Kontoutskrift \n\n");
        
        // add date
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        sb.append("Datum:  " + formatter.format(date) + "\n\n");

        //add account info
        AccountModel am = model.getAccountModel(e.getPno(), e.getAno());
        sb.append("Kontonummer   Saldo   Kontotyp   Ränta \n");
        String accInfo = this.getAccountStr(am);
        sb.append(accInfo);
        System.out.println(sb.toString());

        // create folder
        String folderName = " casvonp_Files";
        Path currentRelativePath = Paths.get(folderName);
        try{
            Files.createDirectory(currentRelativePath);
        } catch (java.nio.file.FileAlreadyExistsException ex){
            // do nothing
        } catch (IOException ie){
            // do nothing
        }

        //write to file
        String filename = "kontoutdrag_" + am.getNr() + ".txt";
        String filePath = folderName + "/" + filename;
        File f = new File(filePath);
        f.delete();
        try {
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");
            writer.println(sb.toString());
            writer.close();        
        } catch (FileNotFoundException fe){
            System.out.println(fe);

        } catch (UnsupportedEncodingException ee){
            System.out.println(ee);
        }
    }





    /**
     * All listener methods from forms
     * 
     */
    @Override
    public void onFindCustomer(FormPanelAction e) {
        String pno = e.getPno();
        model.getCustomers(pno);
    }
    
    @Override
    public void onCloseAccount(FormPanelAction e) {
        String pno = e.getPno();
        String ano = e.getAno();
        model.closeAccount(pno, ano);
    }
    @Override
    public void onTransactAccount(FormPanelAction e) {
        String pno = e.getPno();
        String ano = e.getAno();
        double amount = e.getTransaction_amount();
        model.transactAccount(pno, ano, amount);
    }

    /**
     * Listener method from the ServiceProvider (Model) the bank DB.
     * 
     */
    @Override
    public void onModelEvent(ModelEvent e) {

        if(e.getAction().equals(ModelEvent.DELETE)) {
            // send empty customermodel
            this.fPanel.updatePanelData(new CustomerModel(e.getPno(), "", ""));
        } else {
            if (e.getCustomers().size() > 1){
                this.fPanel.updatePanelData(new CustomerModel("", "", ""));
            }
            if (e.getCustomers().size() == 1){
                if (e.getAction().equals(ModelEvent.CLOSE)){
                    this.fPanel.updatePanelData(e.getCustomers().get(0));;
                }
                if (e.getAction().equals(ModelEvent.TRANSACT)){
                    this.fPanel.updatePanelData(e.getCustomers().get(0));;
                }
                if (e.getAction().equals(ModelEvent.CREATE_ACCOUNT)){
                    this.fPanel.updatePanelData(e.getCustomers().get(0));;
                }
                if (e.getAction().equals(ModelEvent.FIND)){
                    this.fPanel.updatePanelData(e.getCustomers().get(0));;
                }
            }
            if (e.getCustomers().size() == 0){
                this.fPanel.updatePanelData(new CustomerModel(e.getPno(), "", ""));
            }
        }

        List<CustomerModel> customers = e.getCustomers();
        this.lPanel.setText(this.getCustomersStr(customers));
    }

    /**
     * Helper method to print all data as text in the listPanel in the UI
     * 
     * @param customers List<CustomerModel> all customers and its data in the model
     */
    private String getCustomersStr(List<CustomerModel> customers){
        StringBuilder sb = new StringBuilder();
        for (CustomerModel cm : customers){
            sb.append("\n" + cm.toString() + "\n");
            for (AccountModel am : cm.getAccounts()){
                sb.append(getAccountStr(am));
            }
        }
        return sb.toString();
    }

    private String getAccountStr(AccountModel am){
        StringBuilder sb = new StringBuilder();
        if (!am.isClosed()){
            sb.append("   " + am.toString() + "\n");
        }
        else if (am.isClosed()){
            sb.append("   CLOSED: " + am.toString() + "\n");
        }
        for (TransactionModel tm : am.getTransactions()){
            sb.append("      " + tm.toString() + "\n");
        }
        return sb.toString();
    }

    public CustomerPanel getCustomerPanel() {
        return this.fPanel.getCustomerPanel();
    }
    public AccountPanel getAccountPanel(){
        return this.fPanel.getAccountPanel();
    }


}