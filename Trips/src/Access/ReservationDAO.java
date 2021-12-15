/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.ReservationModel;
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
public class ReservationDAO {
    private Connection conn = null;
    
    
    public void insertReservation(ReservationModel reservation) {
        
        try{

            conn = ConnectionDB.getConnection();
            
            String sql = "insert into reservation(passenger_id_fk, trip_id_fk) values (?,?);";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            
            
                statement.setLong(1, reservation.getPassengerIDFk());
                statement.setInt(2, reservation.getTripIDFk());
                
            
            
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
    
    
     public ArrayList<SuperModel> getAllReservations(){
        ArrayList<SuperModel> reservationsList = new ArrayList();
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "select * from reservation;";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()){
                SuperModel reservation = new ReservationModel(result.getInt(1), result.getLong(2), result.getInt(3));
                reservationsList.add(reservation);
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
        
        return reservationsList;
    }
    
    
     public ArrayList<SuperModel> getReservationsByFilter(ReservationModel reservation){

        ArrayList<SuperModel> reservationsList = new ArrayList();
        
        try{
            
            
            conn = ConnectionDB.getConnection();
           
            String sql = "select * from reservation where reservation_num like ?";
             
             

            if(reservation.getPassengerIDFk() != 0){
                sql += " and passenger_id_fk ="+reservation.getPassengerIDFk()+"";
            }

            if(reservation.getTripIDFk() != 0){
                sql += " and trip_id_fk ="+reservation.getTripIDFk()+"";
            }
           
            PreparedStatement statement = conn.prepareStatement(sql);
            if(reservation.getReservationNum() == -1){
                statement.setString(1, "%%");
            } else {
                statement.setString(1, "%"+reservation.getReservationNum()+"%");
            }
            
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                SuperModel reserv = new ReservationModel(result.getInt(1), result.getLong(2), result.getInt(3));
                reservationsList.add(reserv);
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
        return reservationsList;
    }
     
     
    public void update(ReservationModel reservation){
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "update reservation set passenger_id_fk=?, trip_id_fk=? where reservation_num=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, reservation.getPassengerIDFk());
            statement.setInt(2, reservation.getTripIDFk());
            statement.setInt(3, reservation.getReservationNum());
            
            
            
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                JOptionPane.showMessageDialog(null,"El registro " + reservation.getReservationNum() + " fue modificado exitosamente");
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
    
    public void delete(int reservationNum){
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "delete from reservation where reservation_num=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, reservationNum);
            
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
