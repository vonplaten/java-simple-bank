package casvonp.model.account;

import java.io.Serializable;

public class CreditAccount extends Account implements Serializable {

    private static final long serialVersionUID = 1L;
    private double debt_rate;

    public CreditAccount() {
        super(/** account_type */
                "Kreditkonto",
                /** minimum_amount */
                -5000,
                /** rate */
                0.5);
        this.debt_rate = 7; // 7%
    }

    @Override
    double getWithdrawFee(double withdraw_amount) {
        return 0;
    }

    @Override
    public synchronized double getInterest() {
        if (this.amount >= 0)
            return this.amount * (this.rate / 100);
        else
            return this.amount * (this.debt_rate / 100);
    }

    /**
     * Returns a String as presentation of the account as follows: 
     * Example: 1001 -4500.0 Kreditkonto 7.0
     * 
     * @return String
     */
    @Override
    synchronized public String toString() {
        double conditional_rate;
        if (this.amount >= 0)
            conditional_rate = this.rate;
        else
            conditional_rate = this.debt_rate;
        return this.accountId + " " + this.amount + " " + this.account_type + " " + conditional_rate;
    }

}