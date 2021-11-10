/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.LuggageModel;
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
public class LuggageDAO {
    private Connection conn = null;
    
    
    public void insertLuggage(LuggageModel luggage) {
        
        try{

            conn = ConnectionDB.getConnection();

            
            String sql = "insert into luggage(luggage_status, passenger_id_fk) values (?,?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,luggage.getLuggageStatus());
            statement.setLong(2, luggage.getPassengerIDFk());
            
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
    
    
     public ArrayList<SuperModel> getAllLuggage(){
        ArrayList<SuperModel> luggageList = new ArrayList();
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "select * from luggage;";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()){
                SuperModel luggage = new LuggageModel(result.getInt(1), result.getString(2), result.getLong(3));
                luggageList.add(luggage);
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
        
        return luggageList;
    }
    
    
     public ArrayList<SuperModel> getLuggageByFilter(int luggageID, String luggageStatus, long passengerIDFk){
        ArrayList<SuperModel> luggageList = new ArrayList();
        int case_ = -1;
        
        try{
            
            conn = ConnectionDB.getConnection();
           
            String sql = "select * from luggage where luggage_status like ?";
            if(luggageID != -1 && passengerIDFk != -1){
                sql += " and luggage_id =? and passengerIDFk=?;";
                case_ = 1;
            } else if(luggageID != -1){
                sql += " and luggage_id =?;";
                case_ = 2;
            } else if(passengerIDFk != -1){
                sql += " and passengerIDFk=?;";
                case_ = 3;
            }
           
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%"+luggageStatus+"%"); 
            switch(case_){
                case 1:
                   statement.setInt(2, luggageID);
                   statement.setLong(3, passengerIDFk);
                   break;
                case 2:
                   statement.setInt(2, luggageID);
                   break;
                case 3:
                   statement.setLong(2, passengerIDFk);
                   break;
            }
            System.out.println(statement.toString());
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                SuperModel luggage = new LuggageModel(result.getInt(1), result.getString(2), result.getLong(3));
                luggageList.add(luggage);
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
        return luggageList;
    }
     
     
    public void update(int luggageID, String luggageStatus, long passengerIDFk){
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "update luggage set luggage_status=?, passenger_id_fk=? where luggage_id=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, luggageStatus);
            statement.setLong(2, passengerIDFk);
            statement.setInt(3, luggageID);
            
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                JOptionPane.showMessageDialog(null,"El registro " + luggageID + " fue modificado exitosamente");
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
    
    public void delete(int luggageID){
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "delete from luggage where luggage_id=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, luggageID);
            
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
