/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trips;

import Access.BusDAO;
import Access.CityDAO;
import Access.EmployeeDAO;
import Access.LookUpDAO;
import Access.LuggageDAO;
import Access.PassengerDAO;
import Access.ReservationDAO;
import Access.TripDAO;
import Model.BusModel;
import Model.CityModel;
import Model.EmployeeModel;
import Model.LuggageModel;
import Model.PassengerModel;
import Model.ReservationModel;
import Model.TripModel;
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
        TripModel tripMod = new TripModel();
        TripDAO tripDAO = new TripDAO();
        ReservationModel resMod = new ReservationModel();
        ReservationDAO resDAO = new ReservationDAO();
        LookUpDAO lookUpDAO = new LookUpDAO();
        
       
        
        Controller cont = new Controller(mainframe, empMod, empDAO, pasMod, pasDAO, lugMod, lugDAO, busMod, busDAO, cityMod, cityDAO, tripMod, tripDAO, resMod, resDAO, lookUpDAO);
        cont.initialize();
        
        
    }
    
}
