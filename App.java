package casvonp;


import javax.swing.SwingUtilities;

import casvonp.ui.Controller;

/**
 * The class creates the Controller in the MVC pattern. 
 * The class is run in a own thread, becausee swing components are not thread safe.
 * I want to have the app run in the Event Dispatching Thread, and by using "invokeLater"
 * the redraw of the gui is executed on that thread.
 * 
 * Population is done inside the bank ServiceProvider when instantiated.
 * 
 * MainFrame is static.
 * 
 * @author Casimir von Platen 
 * @version 0.1
 */
public class App 
{

    protected Controller controller;

    public App(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                controller = new Controller();
            }
        });
    }



public static void main( String[] args )
{
    new App();
}}