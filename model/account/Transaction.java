package casvonp.model.account;

import java.util.Date;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * Transaction object, one for every transaction made, for storage
 */
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    Date date;
    double amount;
    double account_amount;
    Format dformat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");

    Transaction(double amount, double account_amount) {
        this.date = new Date();
        this.amount = amount;
        this.account_amount = account_amount;
    }

    @Override
    public String toString() {
        return dformat.format(this.date) + " " + this.amount + " " + this.account_amount;
    }

    /**
     * Returns Transactions within a time period It doesn't matter wich order input
     * param dates are
     * 
     * @param ArrayList<Transaction> list of transactions
     * @param Date date_1
     * @param Date date_2
     * @return ArrayList<Transaction>
     */
    static ArrayList<Transaction> getTransactionsByPeriod(ArrayList<Transaction> list, Date date_1, Date date_2) {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t : list) {
            if ((t.date.compareTo(date_1) >= 0 && t.date.compareTo(date_2) <= 0)
                    || (t.date.compareTo(date_2) >= 0 && t.date.compareTo(date_1) <= 0))
                result.add(t);
        }
        return result;
    }        

    static ArrayList<Transaction> getWithdrawsByPeriod(ArrayList<Transaction> list, Date date_1, Date date_2) {
        ArrayList<Transaction> result = new ArrayList<>();
        ArrayList<Transaction> transactions = Transaction.getTransactionsByPeriod(list, date_1, date_2);
        for (Transaction t : transactions){
            if (t.amount < 0)
                result.add(t);
        }
        return result;
    }
}