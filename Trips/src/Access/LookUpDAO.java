/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.SuperModel;
import Model.TripModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import utils.ConnectionDB;

/**
 *
 * @author SANTIAGO SIERRA
 */
public class LookUpDAO {
    
    
    private Connection conn = null;
    
    
    public ArrayList<SuperModel> getAllData(){
        ArrayList<SuperModel> dataList = new ArrayList();
        
        try{

            conn = ConnectionDB.getConnection();
           
            
            //PENDING TO SEE HOW TO DO THE FULL JOIN AND HOW TO CONNECT BUS TO BRING THE SEAT CAPACITY
            String sql = "select trip.trip_id, trip.trip_date, trip.origin_city_fk, trip.destiny_city_fk, trip.price, trip.employee_num_fk, employee.employee_name, reservation.reservation_num, trip.bus_id_fk, luggage.luggage_id, luggage.luggage_status, reservation.passenger_id_fk, passenger.passenger_name\n"+
                         "FROM luggage\n"+
                         "JOIN passenger ON passenger.passenger_id=luggage.passenger_id_fk\n"+
                         "JOIN reservation ON reservation.passenger_id_fk=passenger.passenger_id\n"+
                         "JOIN trip ON trip.trip_id=reservation.trip_id_fk\n"+
                         "JOIN employee ON employee.employee_num=trip.employee_num_fk;";
                    
               
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
       
            while(result.next()){
                SuperModel data = new TripModel(result.getInt(1), result.getDate(2), result.getString(3), result.getString(4), result.getInt(5), result.getInt(6), result.getString(7), result.getInt(8), result.getInt(9), result.getInt(10), result.getString(11), result.getLong(12), result.getString(13));
                dataList.add(data);
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        } finally {
            try{
                conn.close();
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
            }
        }
        
        return dataList;
    }
    
    
    public ArrayList<SuperModel> getFilteredData(TripModel tripMod){
        ArrayList<SuperModel> filteredList = new ArrayList();
        
        try{

            conn = ConnectionDB.getConnection();
           
            
            //PENDING TO SEE HOW TO DO THE FULL JOIN AND HOW TO CONNECT BUS TO BRING THE SEAT CAPACITY
            String sql = "select trip.trip_id, trip.trip_date, trip.origin_city_fk, trip.destiny_city_fk, trip.price, trip.employee_num_fk, employee.employee_name, reservation.reservation_num, trip.bus_id_fk, luggage.luggage_id, luggage.luggage_status, reservation.passenger_id_fk, passenger.passenger_name\n"+
                         "FROM luggage\n"+
                         "JOIN passenger ON passenger.passenger_id=luggage.passenger_id_fk\n"+
                         "JOIN reservation ON reservation.passenger_id_fk=passenger.passenger_id\n"+
                         "JOIN trip ON trip.trip_id=reservation.trip_id_fk\n"+
                         "JOIN employee ON employee.employee_num=trip.employee_num_fk\n"+ 
                         "WHERE trip.origin_city_fk like ?";
            
            if(tripMod.getTripID() != 0){
                sql+= " AND trip.trip_id ="+tripMod.getTripID();
            }
            
            if(tripMod.getTripDate() != null){
                sql+= " AND trip.trip_date ='"+tripMod.getTripDate()+"'";
            }
            
            if(!tripMod.getDestinyCityFk().equals("All Cities")){
                sql+= " AND trip.destiny_city_fk ='"+tripMod.getDestinyCityFk()+"'";
            }
            
            if(tripMod.getPrice() != 0){
                sql+= " AND trip.price ="+tripMod.getPrice()+"";
            }
            
            if(tripMod.getEmployeeNumFk() != 0){
                sql+= " AND trip.employee_num_fk ="+tripMod.getEmployeeNumFk()+"";
            }
            
            if(!tripMod.getEmployeeNameLU().equals("All Employee Names")){
                sql+= " AND employee.employee_name ='"+tripMod.getEmployeeNameLU()+"'";
            }
            
            if(tripMod.getReservationNumLU() != 0){
                sql+= " AND reservation.reservation_num ="+tripMod.getReservationNumLU()+"";
            }
            
            if(tripMod.getBusIDFk() != 0){
                sql+= " AND trip.bus_id_fk ="+tripMod.getBusIDFk()+"";
            }
            
            if(tripMod.getLuggageIDLU() != 0){
                sql+= " AND luggage.luggage_id ="+tripMod.getLuggageIDLU()+"";
            }
            
            if(!tripMod.getLuggageStatusLU().equals("All")){
                sql+= " AND luggage.luggage_status ='"+tripMod.getLuggageStatusLU()+"'"; 
            }
            
            if(tripMod.getPassengerIDLU() != 0){
                sql+= " AND reservation.passenger_id_fk ="+tripMod.getPassengerIDLU()+"";
            }
            
            if(!tripMod.getPassengerNameLU().equals("All Passenger Names")){
                sql+= " AND passenger.passenger_name ='"+tripMod.getPassengerNameLU()+"'";
            }
            
            
            PreparedStatement statement = conn.prepareStatement(sql);
            if(tripMod.getOriginCityFk().equals("All Cities")){
                statement.setString(1, "%%");
            } else {
                statement.setString(1, "%"+tripMod.getOriginCityFk()+"%");
            }
                  
            ResultSet result = statement.executeQuery();
            
       
            while(result.next()){
                SuperModel data = new TripModel(result.getInt(1), result.getDate(2), result.getString(3), result.getString(4), result.getInt(5), result.getInt(6), result.getString(7), result.getInt(8), result.getInt(9), result.getInt(10), result.getString(11), result.getLong(12), result.getString(13));
                filteredList.add(data);
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        } finally {
            try{
                conn.close();
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
            }
        }
        
        
        return filteredList;
    }
    
    
    
}
