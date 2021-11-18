/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trips;

import Access.BusDAO;
import Access.CityDAO;
import Access.EmployeeDAO;
import Access.LuggageDAO;
import Access.PassengerDAO;
import Model.BusModel;
import Model.CityModel;
import Model.EmployeeModel;
import Model.LuggageModel;
import Model.PassengerModel;
import controller.Controller;
import javax.swing.JComboBox;
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
        LuggageModel lugMod = new LuggageModel();
        LuggageDAO lugDAO = new LuggageDAO();
        BusModel busMod = new BusModel();
        BusDAO busDAO = new BusDAO();
        CityModel cityMod = new CityModel();
        CityDAO cityDAO = new CityDAO();
        
       
        
        Controller cont = new Controller(mainframe, empMod, empDAO, pasMod, pasDAO, lugMod, lugDAO, busMod, busDAO, cityMod, cityDAO);
        cont.initialize();
        
        
    }
    
}
