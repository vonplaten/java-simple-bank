package casvonp.ui.panels;

/**
 * A communicator object between the view and controller in the MVC pattern.
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public class FormPanelAction{
    private String pno;
    private String fname;
    private String lname;
    private String ano;
    private double transaction_amount;



    public FormPanelAction(String pno, String fname, String lname){
        this.pno = pno;
        this.fname = fname;
        this.lname = lname;
    }
    public FormPanelAction(String pno, String ano){
        this.pno = pno;
        this.ano = ano;
    }
    public FormPanelAction(String pno, String ano, double amount){
        this.pno = pno;
        this.ano = ano;
        this.transaction_amount = amount;
    }


    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String toString(){
        return this.pno;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public double getTransaction_amount() {
        return transaction_amount;
    }




}