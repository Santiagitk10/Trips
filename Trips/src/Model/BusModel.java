/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author SANTIAGO SIERRA
 */
public class BusModel extends SuperModel {
    
    private int busID;
    private int seatCapacity;

    public BusModel(){
        
    }
    
    public BusModel(int busID, int seatCapacity) {
        this.busID = busID;
        this.seatCapacity = seatCapacity;
    }
   

    public int getBusID() {
        return busID;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }
    
    
    @Override
    public Object[] toArray(){
        Object[] data = {busID,seatCapacity};
        return data;
    }
    
    @Override
    public String toString(){
        return Integer.toString(this.busID);
    }
    
}
