package casvonp.ui.model;


/**
 * Viewmodel for Transaction in the MVC pattern
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public class TransactionModel {
    String date;
    String amount;
    String account_amount;

    public TransactionModel(String date, String amount, String account_amount){
        this.date = date;
        this.amount = amount;
        this.account_amount = account_amount;
    }
    public TransactionModel(String s){
        // [2019-08-27 10:01:17 500.0 500.0]
        String[] ssp = s.split(" ");
        this.date = ssp[0] + " " + ssp[1];
        this.amount = ssp[2];
        this.account_amount = ssp[3];
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAccount_amount() {
        return account_amount;
    }

    public void setAccount_amount(String account_amount) {
        this.account_amount = account_amount;
    }


    @Override
    public String toString() {
        return this.date + " " + this.amount + " " + this.account_amount;
    }

    
    
}