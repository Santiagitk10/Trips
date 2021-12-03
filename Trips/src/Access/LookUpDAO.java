/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.SuperModel;
import Model.TripModel;
import java.sql.Connection;
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
}
