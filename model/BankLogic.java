package casvonp.model;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * The class handles customers and their accounts. The BankLogic class should
 * contain one list of all posted customers. The class should contain a number
 * of public methods that handle customers and their accounts.
 * 
 * @author Casimir von Platen 
 * @version 0.1
 */
public class BankLogic implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Customer> customers = new ArrayList<Customer>();



    /**
     * Returns an ArrayList <String> containing a presentation of the bank all
     * customers as follows: [s n 6911258876, Lotta Larsson 7505121231]
     * 
     * @return ArrayList<String>
     */
    public ArrayList<String> getAllCustomers() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < this.customers.size(); i++) {
            result.add(this.customers.get(i).toString());
        }
        return result;
    }

    /**
     * Returns an ArrayList <String> containing a presentation of the bank all
     * customers as follows: [s n 6911258876, Lotta Larsson 7505121231]
     * 
     * @return boolean Returns true if customer was created otherwise false is
     *         returned.
     */
    public boolean createCustomer(String name, String surname, String pNo) {
        if (this.getCustomerObj(pNo) == null) {
            this.customers.add(new Customer(name, surname, pNo));
            return true;
        }
        return false;
    }

    /**
     * The list returned contains information about the customer in the first place
     * in the array list (first name last name social security number) then follows
     * the accounts as t deleted (account balance account type interest rate
     * interest). [Lotta Larsson 7505121231, 1004 0.0 Sparkonto 1.0 0.0, 1005 700.0
     * Sparkonto 1.0 7.0]
     * 
     * @param pNo personal number
     * @return ArrayList<String>
     */
    public ArrayList<String> getCustomer(String pNo) {
        ArrayList<String> r = new ArrayList<>();
        Customer c = getCustomerObj(pNo);
        if (c != null) {
            r.add(c.toString());
            r.addAll(c.getAccounts());
            return r;
        }
        return null;
    }

    /**
     * Changing the name of the selected customer, the parameter pNo indicates which
     * customer is to receive new name.
     * 
     * @param name    first name
     * @param surname surname
     * @param pNo     personal number
     * @return boolean Returns true if the name was changed otherwise returns false
     *         if the customer was not there.
     */
    public boolean changeCustomerName(String name, String surname, String pNo) {
        Customer c = this.getCustomerObj(pNo);
        if (c != null)
            return c.changeName(name, surname);
        return false;
    }

    /**
     * Remove customer completely. Returns a string of customer and account
     * information. Lastly, put the interest in that information. The logic of
     * adding the interest last is a special feature and is not placed under either
     * Customer or SavingsAccount but it is added here.
     * 
     * @param pNo personal number
     * @return ArrayList<String>. The returned list should contain information about
     *         customer in the first place in the Array List (first name last name
     *         per connumber) then follow the accounts that were removed (account
     *         balance account type of interest rate interest rate). Returns zero if
     *         no customer was removed.
     */
    public ArrayList<String> deleteCustomer(String pNo) {
        // Special: All Interests are retrieved and finally added to each account string
        // Did not choose to have that special logic in SavingsAccount
        Customer c = getCustomerObj(pNo);
        if (c != null) {
            double[] interests = c.getInterests();
            ArrayList<String> r = this.getCustomer(pNo);
            for (int i = 0; i < interests.length; i++) {
                r.set(i + 1, r.get(i + 1) + " " + interests[i]);
            }
            if (this.customers.remove(c))
                return r;
        }
        return null;
    }

    /**
     * Creates a saving account for customer with personal identification number pNo.
     * Returned account number is unique.
     * 
     * @param pNo personal number
     * @return int Returns the account number that the created account received.
     *         Alternatively returned –1 if no account was created.
     */
    public int createSavingsAccount(String pNo) {
        Customer c = this.getCustomerObj(pNo);
        if (c != null) {
            return c.createSavingsAccount();
        }
        return -1;
    }

    /**
     * Creates a credit account for customer with personal identification number pNo.
     * Returned account number is unique.
     * 
     * @param pNo personal number
     * @return int Returns the account number that the created account received.
     *         Alternatively returned –1 if no account was created.
     */
    public int createCreditAccount(String pNr){
        Customer c = this.getCustomerObj(pNr);
        if (c != null) {
            return c.createCreditAccount();
        }
        return -1;        
    }
    public ArrayList<String> getTransactions(String pNr, int accountId){
        Customer c = this.getCustomerObj(pNr);
        return c.getAccountTransactions(accountId);
    }

    /**
     * Returns a String containing presentation of the account with account number
     * accountId belonging to the customer pNo (account number balance, account type
     * interest rate).
     * 
     * @param pNo       personal number
     * @param accountId accountnumber
     * @return String Returns zero if account does not exist or does not belong to
     *         customer.
     */
    public String getAccount(String pNo, int accountId) {
        Customer c = this.getCustomerObj(pNo);
        if (c != null)
            return c.getAccount(accountId);
        return null;
    }

    /**
     * Make a deposit in an account with account number IDI belonging to the
     * customer pNo
     * 
     * @param pNo       personal number
     * @param accountId accountnumber
     * @param amount    amount
     * @return boolean Returns true if it went well otherwise false
     */
    public boolean deposit(String pNo, int accountId, double amount) {
        Customer c = this.getCustomerObj(pNo);
        if (c != null)
            return c.deposit(accountId, amount);
        return false;
    }

    /**
     * Make a withdrawal on account with account number accountId belonging to the
     * customer pNo. The withdrawal is only carried out if the balance covers the
     * withdrawal (the balance must not be less than 0)
     * 
     * @param pNo       personal number
     * @param accountId accountnumber
     * @param amount    amount
     * @return boolean Returns true if it went well otherwise false
     */
    public boolean withdraw(String pNo, int accountId, double amount) {
        Customer c = this.getCustomerObj(pNo);
        if (c != null)
            return c.withdraw(accountId, amount);
        return false;
    }

    /**
     * Ends an account with account number IDI belonging to the customer pNo. When M
     * n ends an account, the interest rate is calculated as balance * interest /
     * 100.
     * 
     * @param pNr       personal number
     * @param accountId accountnumber
     * @return String Returns zero if no account was removed
     */
    public String closeAccount(String pNr, int accountId) {
        Customer c = this.getCustomerObj(pNr);
        if (c != null)
            return c.closeAccount(accountId);
        return null;
    }

    /**
     * Private. Returns a customer object.
     * 
     * @param pNo personal number
     * @return Customer
     */
    private Customer getCustomerObj(String pNo) {
        for (int i = 0; i < this.customers.size(); i++) {
            if (this.customers.get(i).pNo.equals(pNo))
                return customers.get(i);
        }
        return null;
    }

}