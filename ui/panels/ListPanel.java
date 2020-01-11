package casvonp.ui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Panel containing text representation of the content inside the bank.
 *  
 * @author Casimir von Platen 
 * @version 0.1
 */

public class ListPanel extends JPanel{

    private static final long serialVersionUID = 1L;

    private JTextArea tArea;

    public ListPanel() {
        tArea = new JTextArea();
        setLayout(new BorderLayout());
        add(new javax.swing.JScrollPane(tArea), BorderLayout.CENTER);
    }
    
    public void appendText(String txt){
        tArea.append(txt);
    }
    public void setText(String t){
        tArea.setText(t);
    }


}