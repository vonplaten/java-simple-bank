package casvonp.ui.model;

import java.util.ArrayList;


/**
 * Viewmodel for Customer in the MVC pattern
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public class CustomerModel{
    private String pno;
    private String fname;
    private String lname;
    private java.util.List<AccountModel> accounts = new ArrayList<AccountModel>();
    

    /**
     * Constructor CustomerModel
     * 
     * @param pno      String    customer number
     * @param fname    String    customer first name
     * @param lname    String    customer lname
     */
    public CustomerModel(String pno, String fname, String lname){
        this.pno = pno;
        this.fname = fname;
        this.lname = lname;
    }

    /**
     * Constructor CustomerModel
     * 
     * @param a        String    A string in format [pno fname lname] to be parsed.
     */
    public CustomerModel(String c){
        // [Kalle Karlsson 8505221898]
        String[] cs = c.split(" ");
        pno = cs[2];
        fname = cs[0];
        lname = cs[1];
    }

    public String getPno() {
        return pno;
    }


    public String getFname() {
        return fname;
    }


    public String getLname() {
        return lname;
    }


    public String toString(){
        return pno + ":" + fname + ":" + lname;
    }



    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public java.util.List<AccountModel> getAccounts() {
        return accounts;
    }

    public void setAccounts(java.util.List<AccountModel> accounts) {
        this.accounts = accounts;
    }
	public void addAccountModel(AccountModel am) {
        this.accounts.add(am);
    }
    
 
}