/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Access.PassengerDAO;
import Model.PassengerModel;
import Model.SuperModel;
import java.util.ArrayList;

/**
 *
 * @author SANTIAGO SIERRA
 */
public class InitialDataComboBoxes {
    
    private ArrayList<SuperModel> passengers = null;
    
    
    
    
    public InitialDataComboBoxes(){
        PassengerDAO passengerDAO = new PassengerDAO();
        this.passengers = passengerDAO.getAllPassengers();
        this.passengers.add(0,new PassengerModel(0,"Todos los Pasajeros"));
        
    } 

    public ArrayList<SuperModel> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<SuperModel> passengers) {
        this.passengers = passengers;
    }
    
    
    
    
    
}
