/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Access.BusDAO;
import Access.CityDAO;
import Access.EmployeeDAO;
import Access.PassengerDAO;
import Access.TripDAO;
import Model.BusModel;
import Model.CityModel;
import Model.EmployeeModel;
import Model.PassengerModel;
import Model.SuperModel;
import Model.TripModel;
import java.util.ArrayList;

/**
 *
 * @author SANTIAGO SIERRA
 */
public class InitialDataComboBoxes {
    
    private ArrayList<SuperModel> passengers = null;
    private ArrayList<SuperModel> employees = null;
    private ArrayList<SuperModel> cities = null;
    private ArrayList<SuperModel> buses = null;
    private ArrayList<SuperModel> trips = null;
    
    
    
    
    public InitialDataComboBoxes(){
        PassengerDAO passengerDAO = new PassengerDAO();
        this.passengers = passengerDAO.getAllPassengers();
        this.passengers.add(0,new PassengerModel(0,"Todos los Pasajeros"));
        
        EmployeeDAO employeeDAO = new EmployeeDAO();
        this.employees = employeeDAO.getAllEmployees();
        this.employees.add(0,new EmployeeModel(0, "Todos los empleados"));
        
        CityDAO cityDAO = new CityDAO();
        this.cities = cityDAO.getAllCities();
        this.cities.add(0,new CityModel("Todas las Ciudades"));
        
        BusDAO busDAO = new BusDAO();
        this.buses = busDAO.getAllBuses();
        this.buses.add(0,new BusModel(0,0));
        
        TripDAO tripDAO = new TripDAO();
        this.trips = tripDAO.getAllTrips();
        this.trips.add(0, new TripModel(0,null,0,"","",0,0));
        
    } 

    public ArrayList<SuperModel> getPassengers() {
        return passengers;
    }

//    public void setPassengers(ArrayList<SuperModel> passengers) {
//        this.passengers = passengers;
//    }

    public ArrayList<SuperModel> getEmployees() {
        return employees;
    }

//    public void setEmployees(ArrayList<SuperModel> employees) {
//        this.employees = employees;
//    }

    public ArrayList<SuperModel> getCities() {
        return cities;
    }

//    public void setCities(ArrayList<SuperModel> cities) {
//        this.cities = cities;
//    }

    public ArrayList<SuperModel> getBuses() {
        return buses;
    }

//    public void setBuses(ArrayList<SuperModel> buses) {
//        this.buses = buses;
//    }

    public ArrayList<SuperModel> getTrips() {
        return trips;
    }

//    public void setTrips(ArrayList<SuperModel> trips) {
//        this.trips = trips;
//    }
    
    
    
    
    
}
