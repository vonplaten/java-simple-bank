package casvonp.ui.menu;


/**
 * Communicate event from menu (view) to mainframe (controller).
 * 
 * @author Casimir von Platen 
 * @version 0.1
 */
public class MenuAction{

    private String pno;
    private int ano;
    private String fname;
    private String lname;
 
    /**
     * Constructor MenuAction
     * 
     * @param observer    ImenuObserver    Used for actions when only pno is required.
     */
    public MenuAction(String pno) {
        this.pno = pno;
    }

    /**
     * Constructor MenuAction
     * 
     * @param pno    String personnumber.
     * @param ano    String account number
     */
    public MenuAction(String pno, int ano){
        this.pno = pno;
        this.ano = ano;
    }

    /**
     * Constructor MenuAction
     * 
     * @param pno    String personnumber.
     * @param fname    String first name
     * @param lname    String last name
     */
    public MenuAction(String pno, String fname, String lname) {
        this.pno = pno;
        this.fname = fname;
        this.lname = lname;
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

    public int getAno() {
        return ano;
    }


    
    
}