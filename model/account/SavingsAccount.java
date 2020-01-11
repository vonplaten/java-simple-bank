package casvonp.model.account;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple Savings Account. The first account created will receive account
 * number 1001, next get account number 1002 and so on. If an account is deleted
 * then no new account will be received for its account.
 * 
 * @author Casimir von Platen 
 * @version 0.1
 */
public class SavingsAccount extends Account implements Serializable{

    private static final long serialVersionUID = 1L;

    public SavingsAccount() {
        super(/** account_type */
                "Sparkonto",
                /** minimum_amount */
                0,
                /** interest rate */
                1);
    }

    @Override
    double getWithdrawFee(double withdraw_amount) {
        Calendar c = Calendar.getInstance();
        Date today = c.getTime();
        c.add(Calendar.YEAR, -1);
        Date prev_year = c.getTime();
        int nr_of_withdrawal_last_365_days = Transaction.getWithdrawsByPeriod(this.transactions, today, prev_year)
                .size();
        if (nr_of_withdrawal_last_365_days < 1) {
            return 0;
        } else {
            return withdraw_amount * 0.02;
        }
    }

    @Override
    public synchronized double getInterest() {
        return this.amount * (this.rate / 100);
    }

    /**
     * Returns a String as presentation of the account as follows: " Savings account
     * 1.0 0.0 "The fields are" account balance account type interest rate interest
     * rate "
     * 
     * @return String
     */
    @Override
    synchronized public String toString() {
        return this.accountId + " " + this.amount + " " + this.account_type + " " + this.rate;
    }
    

}