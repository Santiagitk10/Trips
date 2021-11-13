/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;


import Model.PassengerModel;
import Model.SuperModel;
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
public class PassengerDAO {
    private Connection conn = null;
    
    
    public void insertPassenger(PassengerModel passenger) {
        
        try{

            conn = ConnectionDB.getConnection();

            
            String sql = "insert into passenger(passenger_id, passenger_name) values (?,?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1,passenger.getPassengerId());
            statement.setString(2,passenger.getPassengerName());
            
            System.out.println(statement.toString());
            
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0){
                JOptionPane.showMessageDialog(null,"El registro fue agregado exitosamente");
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
    }
    
   
    
    public ArrayList<SuperModel> getAllPassengers(){
        ArrayList<SuperModel> passengers = new ArrayList();
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "select * from passenger;";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()){
                SuperModel passenger = new PassengerModel(result.getLong(1), result.getString(2));
                passengers.add(passenger);
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
        
        return passengers;
    }
    
    
    
    
    public ArrayList<SuperModel> getPassengersByFilter(long passengerId, String passengerName){
        ArrayList<SuperModel> passengers = new ArrayList();
        int case_ = -1;
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "select * from passenger where passenger_name like ?";
            if(passengerId != -1){
                sql += " and passenger_id =?;";
                case_ = 1;
            }
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%"+passengerName+"%"); 
            switch(case_){
                case 1:
                   statement.setLong(2, passengerId);
                   break;
            }
            System.out.println(statement.toString());
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                SuperModel passenger = new PassengerModel(result.getLong(1), result.getString(2));
                passengers.add(passenger);
            }
            
        }
        
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        } finally {
            try{
                conn.close();
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
            }
        }
        return passengers;
    }
    
    public void update(long passengerId, String passengerName){
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "update passenger set passenger_name=? where passenger_id=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, passengerName);
            statement.setLong(2, passengerId);
            
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                JOptionPane.showMessageDialog(null,"El registro " + passengerId + " fue modificado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null,"El registro no existe");
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
        
    }
    
    public void delete(long passengerId){
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "delete from passenger where passenger_id=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, passengerId);
            
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                JOptionPane.showMessageDialog(null,"El registro fue eliminado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null,"El registro no existe");
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
    }
}
