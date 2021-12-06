/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;



/**
 *
 * @author SANTIAGO SIERRA
 */
public class TripModel extends SuperModel{
    
    //Attributes in the trip table
    private int tripID;
    private Date tripDate;
    private int price;
    private String originCityFk;
    private String destinyCityFk;
    private int employeeNumFk;
    private int busIDFk;
    
    //Additional attributes for the lookup view
    private String employeeNameLU;
    private int reservationNumLU;
//    private int seatCapLU;
    private int luggageIDLU;
    private String luggageStatusLU;
    private long passengerIDLU;
    private String passengerNameLU;
    

    public TripModel() {
    }
    
    
    //Constructor with the attributes in the trip table
    public TripModel(int tripID, Date tripDate, int price, String originCityFk, String destinyCityFk, int employeeNumFk, int busIDFk) {
        this.tripID = tripID;
        this.tripDate = tripDate;
        this.price = price;
        this.originCityFk = originCityFk;
        this.destinyCityFk = destinyCityFk;
        this.employeeNumFk = employeeNumFk;
        this.busIDFk = busIDFk;
    }
    
    //Constructor with the attributes required in the LookUp view
    public TripModel(int tripID, Date tripDate, String originCityFk, String destinyCityFk, int price, int employeeNumFk, String employeeNameLU, int reservationNumLU, int busIDFk, int luggageIDLU, String luggageStatusLU, long passengerIDLU, String passengerNameLU) {
        this.tripID = tripID;
        this.tripDate = tripDate;
        this.price = price;
        this.originCityFk = originCityFk;
        this.destinyCityFk = destinyCityFk;
        this.employeeNumFk = employeeNumFk;
        this.busIDFk = busIDFk;
        this.employeeNameLU = employeeNameLU;
        this.reservationNumLU = reservationNumLU;
//        this.seatCapLU = seatCapLU;
        this.luggageIDLU = luggageIDLU;
        this.luggageStatusLU = luggageStatusLU;
        this.passengerIDLU = passengerIDLU;
        this.passengerNameLU = passengerNameLU;
    }
    
    
    

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOriginCityFk() {
        return originCityFk;
    }

    public void setOriginCityFk(String originCityFk) {
        this.originCityFk = originCityFk;
    }

    public String getDestinyCityFk() {
        return destinyCityFk;
    }

    public void setDestinyCityFk(String destinyCityFk) {
        this.destinyCityFk = destinyCityFk;
    }

    public int getEmployeeNumFk() {
        return employeeNumFk;
    }

    public void setEmployeeNumFk(int employeeNumFk) {
        this.employeeNumFk = employeeNumFk;
    }

    public int getBusIDFk() {
        return busIDFk;
    }

    public void setBusIDFk(int busIDFk) {
        this.busIDFk = busIDFk;
    }

    public String getEmployeeNameLU() {
        return employeeNameLU;
    }

    public void setEmployeeNameLU(String employeeNameLU) {
        this.employeeNameLU = employeeNameLU;
    }

    public int getReservationNumLU() {
        return reservationNumLU;
    }

    public void setReservationNumLU(int reservationNumLU) {
        this.reservationNumLU = reservationNumLU;
    }

    public int getLuggageIDLU() {
        return luggageIDLU;
    }

    public void setLuggageIDLU(int luggageIDLU) {
        this.luggageIDLU = luggageIDLU;
    }

    public String getLuggageStatusLU() {
        return luggageStatusLU;
    }

    public void setLuggageStatusLU(String luggageStatusLU) {
        this.luggageStatusLU = luggageStatusLU;
    }

    public long getPassengerIDLU() {
        return passengerIDLU;
    }

    public void setPassengerIDLU(long passengerIDLU) {
        this.passengerIDLU = passengerIDLU;
    }

    public String getPassengerNameLU() {
        return passengerNameLU;
    }

    public void setPassengerNameLU(String passengerNameLU) {
        this.passengerNameLU = passengerNameLU;
    }
    
    
    
    
    @Override
    public Object[] toArray(int current){
        if(current == 8){
            Object[] data = {tripID, tripDate, originCityFk, destinyCityFk, price, employeeNumFk, employeeNameLU, reservationNumLU, busIDFk, luggageIDLU, luggageStatusLU, passengerIDLU, passengerNameLU};
            return data;
        } else {
            Object[] data2 = {tripID, tripDate, price, originCityFk, destinyCityFk, employeeNumFk, busIDFk};
            return data2;
        }
        
    }
    
    
    @Override
    public String toString(){
        return Integer.toString(this.tripID);
    }
            
    
}


