package casvonp.model.account;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Baseclass Account for different account_types. The first account created will
 * receive account number 1001, next get account number 1002 and so on. If an
 * account is deleted then no new account will be received for its account.
 * 
 * All variables are declared package-private (default, no protected modifier).
 * 
 * @author Casimir von Platen 
 * @version 0.1
 */

public abstract class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    static int accountCounter = 1000; // static is already transient and will not be saved.

    // common
    public final int accountId; // final
    double amount = 0;
    ArrayList<Transaction> transactions = new ArrayList<>();
    static final Object counterLock = new Object();

    // subobject initialized
    String account_type;
    double rate;
    double minimum_amount;

    /**
     * Constructor Account, used by subclasses
     * 
     * @param account_type    String
     * @param minimum_amount double Minimum abount allowed on account
     * @param rate           double Interest rate on the account.
     */
    public Account(String account_type, double minimum_amount, double rate) {
        // increment++ (read,modify,save) is not an atomic operation
        synchronized (counterLock) {
            if (accountCounter == Integer.MAX_VALUE)
                throw new IllegalStateException("counter overflow");
            accountCounter++;
        }
        this.accountId = accountCounter;
        this.account_type = account_type;
        this.rate = rate;
        this.minimum_amount = minimum_amount;
    }


    /**
     * Make a deposit into the account. Returns true if it went well otherwise
     * false. synchronized
     * 
     * @param amount amount to deposit
     * @return boolean
     */
    public synchronized boolean deposit(double amount) {
        this.amount += amount;
        this.logTransaction(amount);
        return true;
    }

    /**
     * Make a withdrawal from the account. Cannot be less than minimum allowed. 
     * Returns true if it went well otherwise false.
     * synchronized
     * 
     * @param amount amount to withdraw
     * @return boolean
     */
    public synchronized boolean withdraw(double amount) {
        if (amount <= 0)
            return false;
        double fee = this.getWithdrawFee(amount);
        if ((this.amount - amount - fee) >= this.minimum_amount) {
            this.amount -= (amount + fee);
            this.logTransaction(-(amount + fee));
            return true;
        } 
        return false;
    }
    /**
     * Abstract method
     * 
     * @return double. Fee for withdrawal, dependent on account type
     */
    abstract double getWithdrawFee(double withdraw_amount);



    /**
     * Returns estimated interest rate. synchronized
     * 
     * @return double
     */
    public abstract double getInterest();

    /**
     * Returns a String list as presentation of the account transactions: " ex:
     * [2017-01-30 09:17:06 -500.0 -500.0, 2017-01-30 09:17:11 -4000.0 -4500.0]
     * 
     * @return ArrayList<String>
     */
    public ArrayList<String> getTransactions() {
        ArrayList<String> r = new ArrayList<>();
        for (Transaction t : this.transactions)
            r.add(t.toString());
        return r;
    }


    /**
     * Saves transaction in format Time change amountAfter 2017-01-30 09:17:06
     * -500.0 -500.0
     * 
     * @param amount Amount of transaction
     */
    void logTransaction(double amount) {
        this.transactions.add(new Transaction(amount, this.amount));
    }



    /**
     * Handles deserialization of Account static field accountCounter
     * While deserialization picks the highest accountId
     * 
     * @param in ObjectInputStream of transaction
     */
    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
        in.defaultReadObject();
        if (accountId > Account.accountCounter)
            Account.accountCounter = accountId;
    }    


}
