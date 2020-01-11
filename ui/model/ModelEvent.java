package casvonp.ui.model;


/**
 * Communicator object between the model and its listeners.
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */
public class ModelEvent{

    public static final String DELETE = "delete";
    public static final String FIND = "find";
    public static final String SAVE = "save";
    public static final String CLOSE = "close";
    public static final String TRANSACT = "transact";
    public static final String CREATE_ACCOUNT = "create_account";

    private String action;
    private String pno;
    private java.util.List<CustomerModel> customers;

    /**
     * Constructor CustomerModel
     * 
     * @param action    String    action taken
     * @param pno       String    customer number
     * @param customers java.util.List<CustomerModel>    list with customers
     */
    public ModelEvent(String action, String pno, java.util.List<CustomerModel> customers){
        this.action = action;
        this.pno = pno;
        this.customers = customers;
    }

    public java.util.List<CustomerModel> getCustomers() {
        return customers;
    }
    public String getPno(){
        return this.pno;
    }
    public String getAction(){
        return this.action;
    }



}