package casvonp.ui.model;

import java.util.ArrayList;


/**
 * Viewmodel for Account in the MVC pattern
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public class AccountModel{
    private String nr, amount, type, rate;
    private java.util.List<TransactionModel> transactions = new ArrayList<TransactionModel>();

    // Interest
    private boolean closed = false;
    private String interest = "";
    
    // UI
    private boolean selected = false;

    /**
     * Constructor AccountModel
     * 
     * @param nr        String    accountnumber
     * @param amount    String    account amount holded
     * @param type        String    account type
     * @param rate        String    account rate in percent
     */
    public AccountModel(String nr, String amount, String type, String rate){
        this.nr = nr;
        this.amount = amount;
        this.type = type;
        this.rate = rate;
    }

    /**
     * Constructor AccountModel
     * 
     * @param a        String    A string in format [nr amount type rate] to be parsed.
     */
    public AccountModel(String a){
        // [1005 -1000.0 Kreditkonto 7.0]
        String[] asp = a.split(" ");
        this.nr = asp[0];
        this.amount = asp[1];
        this.type = asp[2];
        this.rate = asp[3];
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public java.util.List<TransactionModel> getTransactions() {
        return transactions;
    }

    public void setTransactions(java.util.List<TransactionModel> transactions) {
        this.transactions = transactions;
    }
	public void addTransactionModel(TransactionModel tm) {
        this.transactions.add(tm);
    }
    
    @Override
    public String toString(){
        return this.nr + " " + this.amount + " " + this.type + " " + this.rate;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void close(String interest) {
        this.closed = true;
        this.interest = interest;
    }
    public boolean isClosed(){
        return this.closed;
    }


    public String getInterest() {
        return interest;
    }


}