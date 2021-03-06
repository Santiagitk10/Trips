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
public class PassengerModel extends SuperModel {
    private long passengerId;
    private String passengerName;
    
    
    public PassengerModel(){
        this.passengerName = "";
    }

    public PassengerModel(long passengerId, String passengerName){
        this.passengerId = passengerId;
        this.passengerName = passengerName;
    }
    
    
    public long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(long passengerId) {
        this.passengerId = passengerId;
    }
    

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    @Override
    public Object[] toArray(int current){
        Object[] data = {passengerId, passengerName};
        return data;
    }
    
//    Cuando un elemento por ejemplo un combobox espera un string y se le pasa un objeto, este método entra en juego y le retorna la representación en string de ese objeto
    @Override
    public String toString(){
        return Long.toString(this.passengerId);
    }
}
