package casvonp.ui.model;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import casvonp.model.BankLogic;
import casvonp.model.Populator;


/**
 * Serviceprovider for the Database (The Bank).
 * All access to the bank is through this service privider.
 * The app is populated with a testscript from assignment 2.
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public class ModelServiceProvider{
    private java.util.List<IModelObserver> observers;
    private BankLogic bank;

    public ModelServiceProvider(){
        this.observers = new ArrayList<>(20);
        bank = new BankLogic();
    }

    public void addObserver(IModelObserver o){
        this.observers.add(o);
    }
    private void notifyObservers(ModelEvent e) {
        for (IModelObserver observer : observers) {
            observer.onModelEvent(e);
        }        
    }


    /**
     * Collects customers in a model format
     * Also returns the customers
     * Primary way to use this method is to register as observer.
     * Sends all customers to listeners.
     * 
     * @param pno String customer number (if "" then all customers are returned)
     * @return ArrayList<CustomerModel>
     */
    public ArrayList<CustomerModel> getCustomers(String pno){
        ArrayList<CustomerModel> customers = new ArrayList<CustomerModel>();
        List<String> cStr = null;
        if (pno.equals("")){
            cStr = bank.getAllCustomers(); // [Kalle Karlsson 8505221898, Pelle Persson 6911258876, Lotta Larsson 7505121231]
            for (String c : cStr){
                String[] s = c.split(" ");
                String p = s[2];
                CustomerModel cm = getCustomerModel(p);
                if (cm != null)
                    customers.add(cm);
            }
        } else {
            CustomerModel cm = getCustomerModel(pno);
            if (cm != null)
                customers.add(cm);
        }
        this.notifyObservers(new ModelEvent(ModelEvent.FIND,pno, customers));
        return customers;
    }

    /**
     * Delete customer and notifies listeners
     * 
     * @param pno String customer number (if "" then all customers are returned)
     */
    public void deleteCustomer(String pno){
        bank.deleteCustomer(pno);
        this.notifyObservers(new ModelEvent(ModelEvent.DELETE, pno, this.getCustomers("")));
    }

    /**
     * Change customer and notifies listeners
     * 
     * @param pno String customer number
     * @param fname String customer first name
     * @param lname String customer lastname
     */
    public void changeCustomerName(String pno, String fname, String lname){
        boolean exist = this.getCustomers(pno).size() > 0;
        if (exist) {
            bank.changeCustomerName(fname, lname, pno);
        }
        if (!exist){
            try {
                Integer.parseInt(pno);
                bank.createCustomer(fname, lname, pno);
            } catch (NumberFormatException e){
                System.out.println("ModelServiceProvider:: Customer no must be a number.");
            }
        }
        this.notifyObservers(new ModelEvent(ModelEvent.SAVE, pno, this.getCustomers(pno)));
    }


    /**
     * Closes an account and notifies listeners
     * 
     * @param pno String customer number
     * @param ano String account number
     */
	public void closeAccount(String pno, String ano) {
        List<CustomerModel> customers = this.getCustomers(pno);
        CustomerModel cm = customers.get(0);
        List<AccountModel> accounts = cm.getAccounts();
        for (AccountModel am : accounts){
            if (am.getNr().equals(ano)){
                //found account
                String closeInfo = bank.closeAccount(pno, Integer.parseInt(ano));
                String interest = closeInfo.split(" ")[4];
                am.close(interest);
            }
        }
        this.notifyObservers(new ModelEvent(ModelEvent.CLOSE,pno, customers));
    }

    /**
     * Make transaction and notifies listeners
     * 
     * @param pno String customer number
     * @param ano String account number
     * @param amount double amount
     */
    public void transactAccount(String pno, String ano, double amount){
        if (amount > 0){
            bank.deposit(pno, Integer.parseInt(ano), amount);
        }
        if (amount < 0){
            bank.withdraw(pno, Integer.parseInt(ano), - amount);
        }
        this.notifyObservers(new ModelEvent(ModelEvent.TRANSACT, pno, this.getCustomers(pno)));
    }
    public void createSavingAccount(String pno){
        bank.createSavingsAccount(pno);
        this.notifyObservers(new ModelEvent(ModelEvent.TRANSACT, pno, this.getCustomers(pno)));
    }
    public void createCreditAccount(String pno){
        bank.createCreditAccount(pno);
        this.notifyObservers(new ModelEvent(ModelEvent.TRANSACT, pno, this.getCustomers(pno)));
    }

    /**
     * Serialize bank to casvonp_Files/bank.bin
     * 
     */
    public void bankexport() {
        try{
            String folderName = " casvonp_Files";
            java.nio.file.Path currentRelativePath = java.nio.file.Paths.get(folderName);
            try{
                Files.createDirectory(currentRelativePath);
            } catch (FileAlreadyExistsException e){
                // do nothing
            }

			FileOutputStream f = new FileOutputStream(new File(folderName + "/bank.bin"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(bank);
            o.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Deserialize bank to casvonp_Files/bank.bin
     * 
     */
    public void bankimport() {
        try{
            String folderName = " casvonp_Files";
            FileInputStream fi = new FileInputStream(new File(folderName + "/bank.bin"));
			ObjectInputStream oi = new ObjectInputStream(fi);                        
            BankLogic blr = (BankLogic)oi.readObject();
            oi.close();
            this.bank = blr;
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.out.println("Det finns ingen sparad bankdata. Exportera banken först.");
        } catch (IOException e) {
            System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    public void bankpopulate(){
        Populator p = new Populator();
        p.populate(bank);
    }


    private CustomerModel getCustomerModel(String pno){
        ArrayList<String> infoList = bank.getCustomer(pno);
        if (infoList == null)        
            return null;

        // [Kalle Karlsson 8505221898, 1001 -4500.0 Kreditkonto 7.0, 1003 500.0 Sparkonto 1.0, 1005 -1000.0 Kreditkonto 7.0]
        CustomerModel cm = new CustomerModel(infoList.get(0));
        for (int i=1; i<infoList.size(); i++){

            AccountModel am = new AccountModel(infoList.get(i));
            ArrayList<String> transactions = bank.getTransactions(cm.getPno(), Integer.parseInt(am.getNr()));
            // [2019-08-27 10:01:17 500.0 500.0, 2019-08-27 10:01:17 1000.0 1500.0, 2019-08-27 10:01:17 -500.0 1000.0]
            for (String s : transactions){
                TransactionModel tm = new TransactionModel(s);
                am.addTransactionModel(tm);
            }
            cm.addAccountModel(am);
        }
        return cm;
    }

    public AccountModel getAccountModel(String pno, int ano){
        String infoList = bank.getAccount(pno, ano);
        AccountModel am = new AccountModel(infoList);
        ArrayList<String> transactions = bank.getTransactions(pno, Integer.parseInt(am.getNr()));
        for (String s : transactions){
            TransactionModel tm = new TransactionModel(s);
            am.addTransactionModel(tm);
        }
        return am;
    }






   
}