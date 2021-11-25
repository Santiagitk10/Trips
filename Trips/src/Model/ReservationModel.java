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
public class ReservationModel extends SuperModel {
    
    private int reservationNum;
    private long passengerIDFk;
    private int tripIDFk;
   

    public ReservationModel(int reservationNum, long passengerIDFk, int tripIDFk) {
        this.reservationNum = reservationNum;
        this.passengerIDFk = passengerIDFk;
        this.tripIDFk = tripIDFk;
    }

    public ReservationModel() {
    }
    
    
    
    
    
    

    public int getReservationNum() {
        return reservationNum;
    }

    public void setReservationNum(int reservationNum) {
        this.reservationNum = reservationNum;
    }

    public long getPassengerIDFk() {
        return passengerIDFk;
    }

    public void setPassengerIDFk(long passengerIDFk) {
        this.passengerIDFk = passengerIDFk;
    }

    public int getTripIDFk() {
        return tripIDFk;
    }

    public void setTripIDFk(int tripIDFk) {
        this.tripIDFk = tripIDFk;
    }
    
    
    
}
