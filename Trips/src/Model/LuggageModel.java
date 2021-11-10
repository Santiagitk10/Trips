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
public class LuggageModel extends SuperModel{
    
    private int luggageID;
    private String luggageStatus;
    private long passengerIDFk;
    
    
    public LuggageModel(){
        
    }

    public LuggageModel(int luggageID, String luggageStatus, long passengerIDFk) {
        this.luggageID = luggageID;
        this.luggageStatus = luggageStatus;
        this.passengerIDFk = passengerIDFk;
    }
   

    public int getLuggageID() {
        return luggageID;
    }

    public void setLuggageID(int luggageID) {
        this.luggageID = luggageID;
    }

    public String getLuggageStatus() {
        return luggageStatus;
    }

    public void setLuggageStatus(String luggageStatus) {
        this.luggageStatus = luggageStatus;
    }

    public long getPassengerIDFk() {
        return passengerIDFk;
    }

    public void setPassengerIDFk(long passengerIDFk) {
        this.passengerIDFk = passengerIDFk;
    }
    

    @Override
    public Object[] toArray(){
        Object[] data = {luggageID, luggageStatus, passengerIDFk};
        return data;
    }
    
}
