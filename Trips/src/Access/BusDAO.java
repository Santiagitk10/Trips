/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.BusModel;
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
public class BusDAO {
    private Connection conn = null;
    
    
    public void insertBus(BusModel bus) {
        
        try{

            conn = ConnectionDB.getConnection();

            
            String sql = "insert into bus(seat_capacity) values (?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,bus.getSeatCapacity());
            
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
    
    
     public ArrayList<SuperModel> getAllBuses(){
        ArrayList<SuperModel> buses = new ArrayList();
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "select * from bus;";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()){
                SuperModel bus = new BusModel(result.getInt(1), result.getInt(2));
                buses.add(bus);
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
        
        return buses;
    }
    
    
     public ArrayList<SuperModel> getBusesByFilter(int busID, int SeatCapacity){
        ArrayList<SuperModel> buses = new ArrayList();
        int case_ = -1;
        
        try{

            conn = ConnectionDB.getConnection();
            
            String sql = "";
            
            if(busID != -1 && SeatCapacity != -1){
               sql =  "select * from bus where bus_id = ? and seat_capacity =?;";
               case_ = 1;
            } else if(SeatCapacity == -1){
               sql = "select * from bus where bus_id = ?;";
               case_ = 2;
            } else if(busID == -1){
               sql = "select * from bus where seat_capacity =?;";
               case_ = 3;
            }
            
      
            PreparedStatement statement = conn.prepareStatement(sql);
             
            switch(case_){
                case 1:
                   statement.setInt(1, busID);
                   statement.setInt(2, SeatCapacity);
                   break;
                case 2:
                   statement.setInt(1, busID);
                   break;
                case 3:
                   statement.setInt(1, SeatCapacity);
                   break;
            }
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                SuperModel bus = new BusModel(result.getInt(1), result.getInt(2));
                buses.add(bus);
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
        return buses;
    }
     
     
     
    public void update(int busID, int SeatCapacity){
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "update bus set seat_capacity=? where bus_id=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, SeatCapacity);
            statement.setInt(2, busID);
            
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                JOptionPane.showMessageDialog(null,"El registro " + busID + " fue modificado exitosamente");
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
    
    public void delete(int busID){
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "delete from bus where bus_id=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, busID);
            
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
