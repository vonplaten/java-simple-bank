package casvonp.model;

import java.io.Serializable;
import java.util.ArrayList;
import casvonp.model.account.Account;
import casvonp.model.account.CreditAccount;
import casvonp.model.account.SavingsAccount;


/**
 * The Customer class will handle the following information: the customer's first and second
 * name, social security number and a list of all customer accounts.
 *
 * Customer should never return the list of account items as it would mean a
 * t the class that retrieves the list then in theory could change the list and
 * mn loses encapsulation.
 * 
 * @author Casimir von Platen 
 * @version 0.1
 */
public final class Customer implements Serializable{
    private static final long serialVersionUID = 1L;
    final String pNo; // accessed within same package. Instead of getter.
    private String name;
    private String surname;

    private ArrayList<Account> accounts = new ArrayList<>();

    Customer(String name, String surname, String pNo) {
        this.name = name;
        this.surname = surname;
        this.pNo = pNo;
    }

    // /**
    //  * Returns all customer accounts in ArrayList <Account>. WARNING not safe!
    //  * 
    //  * @return ArrayList<Account>
    //  */
    // public ArrayList<Account> getAccountsObj(){
    //     return accounts;
    // }


    /**
     * Adds a account to Customer
     * 
     * @return int Returns the created accountnumber
     */
    int addAccount(Account account) {
        this.accounts.add(account);
        return account.accountId;
    }

    /**
     * Change Customer name
     * 
     * @param name    firstname
     * @param surname lastname
     * @return Only returns true if name or surename is changed
     */
    boolean changeName(String name, String surname) {
        if (!(name.equals(this.name) && surname.equals(this.surname))) {
            this.name = name;
            this.surname = surname;
            return true;
        }
        return false;
    }

    /**
     * Make a deposit in an account with account number IDI belonging to the customer pNo
     * 
     * @return boolean Return true if deposited ok, else false
     */
    boolean deposit(int accountId, double amount) {
        if (getAccountObj(accountId) != null)
            return this.getAccountObj(accountId).deposit(amount);
        return false;
    }

    /**
     * Make a withdrawal on account with account number accountId belonging to the customer pNo.
     * 
     * @return boolean Return true if withdrawn ok, else false
     */
    boolean withdraw(int accountId, double amount) {
        if (getAccountObj(accountId) != null)
            return this.getAccountObj(accountId).withdraw(amount);
        return false;
    }

    /**
     * Returns an array of interest amounts for each account
     * 
     * @return double[] Each element contains the interest amount of each account.
     */
    double[] getInterests() {
        double[] r = new double[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            r[i] = accounts.get(i).getInterest();
        }
        return r;
    }

    /**
     * Stänger ett konto och raderar det
     * 
     * @param accountId kontonummer
     * @return Account Presentation of the account must be returned including the interest rate man
     * get on the money (account number balance, account type interest rate, interest rate). Ret
     * nulls zero if no account was removed
     */
    String closeAccount(int accountId) {
        Account account = this.getAccountObj(accountId);
        if (account != null) {
            double interest = account.getInterest();
            String r = account.toString() + " " + String.format("%.1f", interest);
            boolean deleted = this.accounts.remove(account);
            if (!deleted)
                return null;
            return r;
        }
        return null;
    }

    /**
     * Default encapsulation. Returns an account object
     * 
     * @param accountId accountnumber
     * @return Account
     */
    private Account getAccountObj(int accountId) {
        for (int i = 0; i < this.accounts.size(); i++) {
            if (this.accounts.get(i).accountId == accountId)
                return this.accounts.get(i);
        }
        return null;
    }

    /**
     * Default. Returns an account as string
     * 
     * @param accountId accountnumber
     * @return Account
     */
    String getAccount(int accountId) {
        Account acc = this.getAccountObj(accountId);
        if (acc != null)
            return acc.toString();
        return null;
    }

    ArrayList<String> getAccountTransactions(int accountId){
        Account acc = this.getAccountObj(accountId);
        if (acc != null)
            return acc.getTransactions();
        return null;
    }

    /**
     * Returns all accounts in String format
     * 
     * @return ArrayList<String>
     */
    ArrayList<String> getAccounts() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < this.accounts.size(); i++) {
            result.add(this.accounts.get(i).toString());
        }
        return result;
    }

    /**
     * Returns a String as presentation of the customer as follows: [K
     * lsson 8505221898]
     * 
     * @return String
     */
    @Override
    public String toString() {
        return this.name + " " + this.surname + " " + this.pNo;
    }

	public int createCreditAccount() {
        CreditAccount account = new CreditAccount();
        return this.addAccount(account);
	}

	public int createSavingsAccount() {
        SavingsAccount account = new SavingsAccount();
        return this.addAccount(account);

	}

}
