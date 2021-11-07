/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trips;

import Access.EmployeeDAO;
import Access.PassengerDAO;
import Model.EmployeeModel;
import Model.PassengerModel;
import controller.Controller;
import view.MainFrame;

/**
 *
 * @author SANTIAGO SIERRA
 */
public class Trips {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainFrame mainframe = new MainFrame();
        EmployeeModel empMod = new EmployeeModel();
        EmployeeDAO empDAO = new EmployeeDAO();
        PassengerModel pasMod = new PassengerModel();
        PassengerDAO pasDAO = new PassengerDAO();
        
        Controller cont = new Controller(mainframe, empMod, empDAO, pasMod, pasDAO );
        cont.initialize();
        
        
    }
    
}
