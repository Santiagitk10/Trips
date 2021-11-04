/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trips;

import Access.EmployeeDAO;
import Model.EmployeeModel;
import controller.EmployeeController;
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
        
        EmployeeController empCont = new EmployeeController(mainframe, empMod,empDAO);
        empCont.initialize();
        
        
    }
    
}
